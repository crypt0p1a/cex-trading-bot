package eu.codlab.cex.wallet.logic

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.configuration.BuySellConfiguration
import eu.codlab.cex.database.orders.Order
import eu.codlab.cex.spot.trading.IPrivateApi
import eu.codlab.cex.spot.trading.IPublicApi
import eu.codlab.cex.spot.trading.groups.account.balance.AccountStatusRequest
import eu.codlab.cex.spot.trading.groups.orders.OrderType
import eu.codlab.cex.spot.trading.groups.orders.news.NewOrder
import eu.codlab.cex.spot.trading.groups.orders.news.NewOrderAnswer
import eu.codlab.cex.spot.trading.groups.orders.news.TimeInForce
import eu.codlab.cex.tools.extrapolate.Directions
import eu.codlab.cex.utils.DecimalModeDivide
import eu.codlab.cex.utils.expireTimeFormat
import eu.codlab.cex.utils.findNearestMultiple
import eu.codlab.cex.utils.toBigDecimal
import korlibs.time.DateTime
import korlibs.time.hours
import korlibs.time.months
import kotlin.math.abs

class BuyOrder(
    private val wallet: String,
    private val publicApi: IPublicApi,
    private val privateApi: IPrivateApi,
    private val pairConfiguration: PairConfiguration,
    private val enabledPairForWallet: List<PairConfiguration>,
    private val logger: Logger
) : Logic<Order?> {
    @Suppress("LongMethod", "ReturnCount", "MagicNumber")
    override suspend fun execute(previous: Order?, trend: Directions) {
        if (!enabledPairForWallet.any { it.leftRight == pairConfiguration.leftRight }) {
            logger.log("pair disabled")
            return
        }

        if (!canContinueBuyOrderLogic(previous)) {
            logger.log("need to wait order timeout")
            return
        }

        /*
        // we actually don't want to do this as it would be problematic actually
        // Note : improvement could be to just don't try to make a buy attempt in a window :
        // for instance the time interval between the "we can buy" up to 2h max and then pass it
        // anyway
        if (!trend.isDown) {
            logger.log("skipping buy order, trending is not really down ($trend)")
            logger.log("  we'll wait that we can expect a buy order to pass in a down ctx")
            return
        }
        */

        val currentWallet = privateApi.getMyAccountStatus(
            AccountStatusRequest(
                accountIds = setOf(wallet)
            )
        ).balancesPerAccounts[wallet]!!

        val currentWalletValue = currentWallet
            .mapNotNull { it.value.balanceInConvertedCurrency }
            .sum()
            .toBigDecimal()

        val availableBalance = currentWallet[pairConfiguration.right.name]?.let {
            it.balance - it.balanceOnHold
        }?.toBigDecimal() ?: BigDecimal.ZERO

        val info = computeExpectedWeight()

        if (currentWalletValue == BigDecimal.ZERO) {
            logger.log("can't make a buy order, current wallet value is 0")
            return
        }

        logger.log("info $info")
        logger.log("currentWalletValue ${currentWalletValue.toPlainString()}")
        logger.log("availableBalance ${availableBalance.toPlainString()}")

        val calculatedAmountToConsume =
            currentWalletValue.divide(info.totalWeight.toBigDecimal(), DecimalModeDivide)
                .multiply(info.currentWeight.toBigDecimal()).let { calculated ->
                    if (calculated > availableBalance) {
                        logger.log("falling back to the availableBalance of ${availableBalance.toPlainString()}")
                        availableBalance
                    } else {
                        calculated
                    }
                }


        val minimumAmountToConsume = info.minimumValueInCurrency.toBigDecimal()

        logger.log("expected amount to consume : ${calculatedAmountToConsume.toPlainString()}")
        logger.log("minimum amount to consume : ${minimumAmountToConsume.toPlainString()}")

        val amount = if (calculatedAmountToConsume < minimumAmountToConsume) {
            minimumAmountToConsume
        } else {
            calculatedAmountToConsume
        }

        logger.log("will use ${amount.toPlainString()}")

        val (consumed, balance) = amount.toStringExpanded() to availableBalance.toStringExpanded()

        if (consumed == balance) {
            logger.log("will use all wallet available")
        } else if (amount > availableBalance) {
            logger.log("couldn't buy anything, not enough money $consumed != $balance")
            return
        }

        assert(minimumAmountToConsume > currentWalletValue)
        assert(amount >= availableBalance)

        // now we can compute what will be the amount in the other currency
        val pairInfo = publicApi.pairsInfo(pairConfiguration.leftRight).first()

        // using the ticker because of the 24h of data for each
        val contextPair = pairConfiguration.leftRight.let { pair ->
            publicApi.tickers(pair)[pair]!!
        }

        assert(contextPair.low < contextPair.high)

        if (contextPair.last > contextPair.high) {
            logger.log("skipping this tick, the pair's last is higher than the high. Unexpected")
            return
        }

        val mean = contextPair.last.toBigDecimal()
            .plus(contextPair.high.toBigDecimal()).divide(2.0.toBigDecimal(), DecimalModeDivide)

        val conf = pairConfiguration.configuration
        val priceBuy = when (conf) {
            is BuySellConfiguration.Absolute -> conf.buyAt.toBigDecimal()
            is BuySellConfiguration.Ratio -> mean.multiply(conf.buyCoef.toBigDecimal())
        }

        val priceBuyRounded = priceBuy.roundToDigitPositionAfterDecimalPoint(
            pairInfo.pricePrecision.toLong(),
            RoundingMode.FLOOR
        )

        logger.log("priceBuy        -> ${priceBuy.toStringExpanded()}")
        logger.log("priceBuyRounded -> ${priceBuyRounded.toStringExpanded()}")

        assert(priceBuy < mean)
        assert(priceBuyRounded < mean)

        // take a lower part of the amount to buy (-> buy 99%)
        val opponentFeeRatio = 0.99
        val amountToBuy = amount.divide(priceBuy, DecimalModeDivide)
            .multiply(BigDecimal.fromDouble(opponentFeeRatio))
        val amountToBuyRounded = amountToBuy.roundToDigitPositionAfterDecimalPoint(
            pairInfo.basePrecision.toLong(),
            RoundingMode.FLOOR
        )

        val amountToBuyAdjustedMultiple = amountToBuyRounded.findNearestMultiple(
            pairInfo.baseLotSize.toBigDecimal()
        )

        logger.log("amountToBuy        -> ${amountToBuy.toStringExpanded()}")
        logger.log("amountToBuyRounded -> ${amountToBuyRounded.toStringExpanded()}")
        logger.log("  now comparing with baseLotSize ${pairInfo.baseLotSize}")
        logger.log("amountToBuyAdjustedMultiple -> ${amountToBuyAdjustedMultiple.toStringExpanded()}")

        if (amountToBuyAdjustedMultiple == BigDecimal.ZERO) {
            logger.log("can't buy ${pairConfiguration.leftRight}, amount would be 0")
            return
        }

        if (amountToBuyAdjustedMultiple < pairInfo.baseMin) {
            logger.log(
                "can't buy ${pairConfiguration.leftRight}, amount ${amountToBuyRounded.toStringExpanded()}" +
                        "is inferior to ${pairInfo.baseMin}"
            )
            return
        }

        val clientOrderId = "bot_buy_${System.currentTimeMillis()}"
        val expireTime = DateTime.now().add(
            0.months,
            pairConfiguration.cancelBuyOrderTimeout
        )

        val order: NewOrderAnswer = privateApi.newOrder(
            NewOrder(
                clientOrderId = clientOrderId,
                accountId = wallet,
                currency1 = pairConfiguration.left.name,
                currency2 = pairConfiguration.right.name,
                side = eu.codlab.cex.spot.trading.groups.orders.OrderSide.Buy,
                orderType = OrderType.Limit,
                amountCcy1 = amountToBuyAdjustedMultiple.toStringExpanded(),
                // amountCcy1 = null,
                // amountCcy2 = amountToConsume.toStringExpanded(),
                amountCcy2 = null,
                price = priceBuyRounded.toStringExpanded(),
                comment = "consume[${amount.toStringExpanded()}] " +
                        "priceBuy[${priceBuyRounded.toStringExpanded()}]" +
                        "amountBuy[${amountToBuyAdjustedMultiple.toStringExpanded()}] " +
                        pairConfiguration.configuration.toStringBuy(),
                timeInForce = TimeInForce.GTD,
                expireTime = expireTime.format(expireTimeFormat),
            ).also { logger.log("$it") }
        ).also { logger.log("$it") }

        if (order.status == eu.codlab.cex.spot.trading.models.OrderStatus.REJECTED) {
            logger.log("order rejected !")
            logger.log("    pairInfo -> $pairInfo")
            logger.log("    contextPair -> $contextPair")
            logger.log("    order attempt -> $order")
            return
        }
    }

    private fun computeExpectedWeight() = EnvToBuyAsset(
        totalWeight = enabledPairForWallet.sumOf { pair -> pair.balanceWeightUsed },
        currentWeight = pairConfiguration.balanceWeightUsed,
        minimumValueInCurrency = pairConfiguration.minimumBalanceUsed
    )

    private fun canContinueBuyOrderLogic(previous: Order?): Boolean {
        if (null == previous) return true

        val diffMs = abs(DateTime.nowUnixMillisLong() - previous.lastUpdateTimestamp)

        return diffMs > 6.hours.millisecondsLong
    }

    private data class EnvToBuyAsset(
        val currentWeight: Int,
        val totalWeight: Int,
        val minimumValueInCurrency: Double
    )
}

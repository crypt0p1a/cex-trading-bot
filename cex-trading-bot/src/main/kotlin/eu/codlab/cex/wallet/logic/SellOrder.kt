package eu.codlab.cex.wallet.logic

import com.ionspin.kotlin.bignum.decimal.RoundingMode
import eu.codlab.cex.PairConfiguration
import eu.codlab.cex.database.orders.Order
import eu.codlab.cex.spot.trading.IPrivateApi
import eu.codlab.cex.spot.trading.IPublicApi
import eu.codlab.cex.spot.trading.groups.orders.OrderSide
import eu.codlab.cex.spot.trading.groups.orders.OrderType
import eu.codlab.cex.spot.trading.groups.orders.news.NewOrder
import eu.codlab.cex.spot.trading.groups.orders.news.NewOrderAnswer
import eu.codlab.cex.spot.trading.groups.orders.news.TimeInForce
import eu.codlab.cex.spot.trading.models.OrderStatus
import eu.codlab.cex.utils.DecimalModeDivide
import eu.codlab.cex.utils.toBigDecimal

class SellOrder(
    private val wallet: String,
    private val publicApi: IPublicApi,
    private val privateApi: IPrivateApi,
    private val pairConfiguration: PairConfiguration,
    private val logger: Logger
) : Logic<Order> {
    override suspend fun execute(previous: Order) {
        val requestedAmountCcy1 = previous.requestedAmountCcy1!!.toStringExpanded()
        val buyPrice = previous.price!!

        // we originally set priceToBuy = mean * buyCoef
        // so we need to invert this

        val originalAmountRight = previous.requestedAmountCcy1!!.multiply(buyPrice)
            .divide(pairConfiguration.buyCoef.toBigDecimal(), DecimalModeDivide)

        val originalPrice =
            buyPrice.divide(pairConfiguration.buyCoef.toBigDecimal(), DecimalModeDivide)
        val sellPrice = originalPrice.multiply(pairConfiguration.sellCoef.toBigDecimal())

        logger.log("originalAmountRight ${originalAmountRight.toPlainString()}")
        logger.log("buyPrice ${buyPrice.toPlainString()}")

        if (sellPrice < buyPrice) {
            logger.log(
                "can't make a sell order, invalid data for pricing ! please check" +
                        "sellPrice:${sellPrice.toStringExpanded()} / buyPrice:${buyPrice.toStringExpanded()}"
            )
            return
        }

        val pairInfo = publicApi.pairsInfo(pairConfiguration.leftRight).first()

        val sellAt = sellPrice.roundToDigitPositionAfterDecimalPoint(
            pairInfo.pricePrecision.toLong(),
            RoundingMode.FLOOR
        )

        logger.log("sellAt : ${sellAt.toPlainString()}")
        logger.log("requestedAmountCcy1 : $requestedAmountCcy1")

        logger.log("pairInfo $pairInfo")

        val clientOrderId = "bot_sell_${System.currentTimeMillis()}"
        val order: NewOrderAnswer = privateApi.newOrder(
            NewOrder(
                clientOrderId = clientOrderId,
                accountId = wallet,
                currency1 = pairConfiguration.left.name,
                currency2 = pairConfiguration.right.name,
                side = OrderSide.Sell,
                orderType = OrderType.Limit,
                amountCcy1 = requestedAmountCcy1,
                // amountCcy1 = null,
                // amountCcy2 = amountToConsume.toStringExpanded(),
                amountCcy2 = null,
                price = sellAt.toStringExpanded(),
                comment = "priceSell[${sellAt.toStringExpanded()}]" +
                        "amountSell[$requestedAmountCcy1] " +
                        "sellCoef[${pairConfiguration.sellCoef}]",
                timeInForce = TimeInForce.GTC
            ).also {
                logger.log("new order : $it")
            }
        ).also {
            logger.log("result : $it")
        }

        if (order.status == OrderStatus.REJECTED) {
            val contextPair = pairConfiguration.leftRight.let { pair ->
                publicApi.tickers(pair)[pair]!!
            }

            logger.log("order rejected !")
            logger.log("    pairInfo -> $pairInfo")
            logger.log("    contextPair -> $contextPair")
            logger.log("    order attempt -> $order")
            return
        }
    }
}

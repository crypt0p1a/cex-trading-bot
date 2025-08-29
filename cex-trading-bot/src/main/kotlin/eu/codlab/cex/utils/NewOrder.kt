package eu.codlab.cex.utils

import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import eu.codlab.cex.database.orders.Order
import eu.codlab.cex.spot.trading.groups.orders.OrderSide
import eu.codlab.cex.spot.trading.groups.orders.OrderType
import eu.codlab.cex.spot.trading.groups.orders.news.NewOrderAnswer
import eu.codlab.cex.spot.trading.models.OrderResult

import eu.codlab.cex.database.orders.OrderSide as OS
import eu.codlab.cex.database.orders.OrderType as OT

fun NewOrderAnswer.toOrder() = Order(
    id = 0,
    orderId = orderId,
    clientOrderId = clientOrderId,
    clientId = clientId,
    accountId = accountId,
    status = status.toDatabase,
    statusIsFinal = false,
    currency1 = currency1,
    currency2 = currency2,
    side = side.toDatabase,
    orderType = orderType.toDatabase,
    timeInForce = timeInForce?.toDatabase,
    comment = comment,
    rejectCode = rejectCode,
    rejectReason = rejectReason,
    executedAmountCcy1 = executedAmountCcy1?.toBigDecimal(),
    executedAmountCcy2 = executedAmountCcy2?.toBigDecimal(),
    requestedAmountCcy1 = requestedAmountCcy1?.toBigDecimal(),
    requestedAmountCcy2 = requestedAmountCcy2?.toBigDecimal(),
    initialOnHoldAmountCcy1 = null,
    initialOnHoldAmountCcy2 = null,
    feeAmount = feeAmount?.toBigDecimal(),
    feeCurrency = feeCurrency,
    price = price?.toBigDecimal(),
    averagePrice = averagePrice?.toBigDecimal(),
    clientCreateTimestamp = timestamp,
    serverCreateTimestamp = timestamp,
    lastUpdateTimestamp = timestamp,
    expireTime = expireTime,
    effectiveTime = effectiveTime,
)

val OrderSide.toDatabase: OS
    get() = when (this) {
        OrderSide.Buy -> OS.BUY
        OrderSide.Sell -> OS.SELL
    }

val OrderType.toDatabase: OT
    get() = when (this) {
        OrderType.Limit -> OT.LIMIT
        OrderType.Market -> OT.MARKET
    }
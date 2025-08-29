package eu.codlab.cex.utils

import eu.codlab.cex.database.orders.Order
import eu.codlab.cex.spot.trading.groups.orders.news.TimeInForce
import eu.codlab.cex.spot.trading.models.OrderResult
import eu.codlab.cex.spot.trading.models.OrderStatus
import eu.codlab.cex.database.orders.OrderStatus as OS
import eu.codlab.cex.database.orders.TimeInForce as TIF

fun OrderResult.toOrder() = Order(
    id = 0,
    orderId = orderId,
    clientOrderId = clientOrderId,
    clientId = clientId,
    accountId = accountId,
    status = status.toDatabase,
    statusIsFinal = statusIsFinal,
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
    initialOnHoldAmountCcy1 = initialOnHoldAmountCcy1?.toBigDecimal(),
    initialOnHoldAmountCcy2 = initialOnHoldAmountCcy2?.toBigDecimal(),
    feeAmount = feeAmount?.toBigDecimal(),
    feeCurrency = feeCurrency,
    price = price?.toBigDecimal(),
    averagePrice = averagePrice?.toBigDecimal(),
    clientCreateTimestamp = clientCreateTimestamp,
    serverCreateTimestamp = serverCreateTimestamp,
    lastUpdateTimestamp = lastUpdateTimestamp,
    expireTime = expireTime,
    effectiveTime = effectiveTime,
)

val TimeInForce.toDatabase: TIF
    get() = when (this) {
        TimeInForce.GTC -> TIF.GTC
        TimeInForce.IOC -> TIF.IOC
        TimeInForce.GTD -> TIF.GTD
    }

val OrderStatus.toDatabase: OS
    get() = when (this) {
        OrderStatus.PENDING_NEW -> OS.PENDING_NEW
        OrderStatus.NEW -> OS.NEW
        OrderStatus.PARTIALLY_FILLED -> OS.PARTIALLY_FILLED
        OrderStatus.FILLED -> OS.FILLED
        OrderStatus.EXPIRED -> OS.EXPIRED
        OrderStatus.REJECTED -> OS.REJECTED
        OrderStatus.PENDING_CANCEL -> OS.PENDING_CANCEL
        OrderStatus.CANCELLED -> OS.CANCELLED
    }
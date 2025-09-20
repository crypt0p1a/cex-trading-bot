package eu.codlab.cex

import eu.codlab.cex.configuration.BuySellConfiguration

val Pairs = listOf(
    PairConfiguration(
        left = Symbol.USDC,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Absolute(
            buyAt = 0.9999,
            sellAt = 1.001,
        ),
    ),
    PairConfiguration(
        left = Symbol.BTC,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.985,
            sellCoef = 1.015,
        ),
    ),
    PairConfiguration(
        left = Symbol.ETH,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.985,
            sellCoef = 1.015,
        ),
    ),
    PairConfiguration(
        left = Symbol.XRP,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.985,
            sellCoef = 1.015,
        ),
    ),
    PairConfiguration(
        left = Symbol.LTC,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.985,
            sellCoef = 1.015,
        ),
    ),
    PairConfiguration(
        left = Symbol.DOGE,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.985,
            sellCoef = 1.015,
        ),
    ),
    PairConfiguration(
        left = Symbol.ETHFI,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.99,
            sellCoef = 1.02,
        ),
    ),
)

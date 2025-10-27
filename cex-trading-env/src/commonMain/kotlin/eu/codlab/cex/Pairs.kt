package eu.codlab.cex

import eu.codlab.cex.configuration.BuySellConfiguration

val Pairs = listOf(
    PairConfiguration(
        left = Symbol.BTC,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
    ),
    PairConfiguration(
        left = Symbol.ETH,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
    ),
    PairConfiguration(
        left = Symbol.XRP,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
    ),
    PairConfiguration(
        left = Symbol.LTC,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
    ),
    PairConfiguration(
        left = Symbol.DOGE,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
    ),
    PairConfiguration(
        left = Symbol.ETHFI,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
    ),
    PairConfiguration(
        left = Symbol.SOL,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
    ),
)

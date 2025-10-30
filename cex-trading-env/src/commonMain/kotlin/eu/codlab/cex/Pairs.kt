package eu.codlab.cex

import eu.codlab.cex.configuration.BuySellConfiguration
import eu.codlab.cex.configuration.FallbackStrategy

val defaultFallbackStrategy = FallbackStrategy(
    buy = FallbackStrategy.BuyStrategy(
        pairMinRatio = 1.1 /* will use min + 10% */
    )
)

val Pairs = listOf(
    PairConfiguration(
        left = Symbol.BTC,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.994,
            sellCoef = 1.006,
        ),
        fallbackStrategy = defaultFallbackStrategy
    ),
    PairConfiguration(
        left = Symbol.ETH,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
        fallbackStrategy = defaultFallbackStrategy
    ),
    PairConfiguration(
        left = Symbol.XRP,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
        fallbackStrategy = defaultFallbackStrategy
    ),
    PairConfiguration(
        left = Symbol.LTC,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
        fallbackStrategy = defaultFallbackStrategy
    ),
    PairConfiguration(
        left = Symbol.DOGE,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
        fallbackStrategy = defaultFallbackStrategy
    ),
    PairConfiguration(
        left = Symbol.ETHFI,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.992,
            sellCoef = 1.01,
        ),
        fallbackStrategy = defaultFallbackStrategy
    ),
    PairConfiguration(
        left = Symbol.SOL,
        right = Symbol.USD,
        configuration = BuySellConfiguration.Ratio(
            buyCoef = 0.995,
            sellCoef = 1.005,
        ),
        fallbackStrategy = defaultFallbackStrategy
    ),
)

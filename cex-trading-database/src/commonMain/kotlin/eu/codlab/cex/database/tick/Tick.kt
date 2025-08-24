package eu.codlab.cex.database.tick

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import eu.codlab.cex.Symbol
import eu.codlab.cex.database.utils.BigDecimalSerializer
import eu.codlab.cex.database.utils.DateTimeSerializer
import korlibs.time.DateTime
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Tick(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val exchange: String,
    @Serializable(with = DateTimeSerializer::class)
    val timestamp: DateTime,
    val left: Symbol,
    val right: Symbol,
    @Serializable(with = BigDecimalSerializer::class)
    val low: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val high: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val last: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val bestBid: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val bestAsk: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val bestBidChange: BigDecimal,
    val bestBidChangePercentage: Double,
    @Serializable(with = BigDecimalSerializer::class)
    val bestAskChange: BigDecimal,
    val bestAskChangePercentage: Double,
    @Serializable(with = BigDecimalSerializer::class)
    val volume: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val volume30d: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val quoteVolume: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val lastTradeVolume: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val lastTradePrice: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val priceChange: BigDecimal,
    val priceChangePercentage: Double,
    val lastTradeDateISO: String,
    @Serializable(with = BigDecimalSerializer::class)
    val volumeUSD: BigDecimal,
)

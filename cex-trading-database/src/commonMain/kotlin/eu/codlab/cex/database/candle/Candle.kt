package eu.codlab.cex.database.candle

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
data class Candle(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val exchange: String,
    @Serializable(with = DateTimeSerializer::class)
    val timestamp: DateTime,
    val left: Symbol,
    val right: Symbol,
    @Serializable(with = BigDecimalSerializer::class)
    val open: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val high: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val low: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val close: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val volume: BigDecimal,
)

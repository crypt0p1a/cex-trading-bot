package eu.codlab.cex.database.utils

import androidx.room.TypeConverter
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import korlibs.time.DateTime

class Converters {
    @TypeConverter
    fun fromLongToDateTime(value: Long?): DateTime? {
        return value?.let { DateTime(it) }
    }

    @TypeConverter
    fun fromDateTimeToLong(date: DateTime?): Long? {
        return date?.unixMillisLong
    }

    @TypeConverter
    fun fromStringToBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal.parseString(it) }
    }

    @TypeConverter
    fun fromBigDecimalToString(bigDecimal: BigDecimal?): String? {
        return bigDecimal?.toStringExpanded()
    }
}
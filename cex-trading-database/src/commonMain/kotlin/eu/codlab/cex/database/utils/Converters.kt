package eu.codlab.cex.database.utils

import androidx.room.TypeConverter
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import korlibs.time.DateTime

class Converters {
    @TypeConverter
    fun fromLongToDateTime(value: Long?) = value?.let { DateTime(it) }

    @TypeConverter
    fun fromDateTimeToLong(date: DateTime?) = date?.unixMillisLong

    @TypeConverter
    fun fromStringToBigDecimal(value: String?) = value?.let { BigDecimal.parseString(it) }

    @TypeConverter
    fun fromBigDecimalToString(bigDecimal: BigDecimal?) = bigDecimal?.toStringExpanded()
}

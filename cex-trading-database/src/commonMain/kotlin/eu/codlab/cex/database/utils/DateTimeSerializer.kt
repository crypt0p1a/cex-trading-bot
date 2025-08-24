package eu.codlab.cex.database.utils

import korlibs.time.DateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DateTimeSerializer : KSerializer<DateTime> {
    override val descriptor = PrimitiveSerialDescriptor("DateTime", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): DateTime {
        return DateTime.fromUnixMillis(decoder.decodeLong())
    }

    override fun serialize(encoder: Encoder, value: DateTime) {
        encoder.encodeLong(value.unixMillisLong)
    }
}
package com.technologygardens.pickyourown.codecs

import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.springframework.context.annotation.Profile
import java.time.ZonedDateTime

@Profile("dev-mongo", "prod-mongo")
class ZonedDateTimeCodec : Codec<ZonedDateTime> {
    override fun getEncoderClass(): Class<ZonedDateTime> = ZonedDateTime::class.java

    override fun encode(writer: BsonWriter, value: ZonedDateTime, encoderContext: EncoderContext) {
        writer.writeString(value.toString())
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): ZonedDateTime {
        return ZonedDateTime.parse(reader.readString())
    }
}
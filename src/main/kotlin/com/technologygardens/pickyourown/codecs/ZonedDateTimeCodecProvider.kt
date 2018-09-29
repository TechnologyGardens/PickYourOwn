package com.technologygardens.pickyourown.codecs

import org.bson.codecs.BsonTypeClassMap
import org.bson.codecs.Codec
import org.bson.codecs.DocumentCodec
import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.configuration.CodecRegistry
import org.springframework.context.annotation.Profile
import java.time.ZonedDateTime

@Profile("dev-mongo", "prod-mongo")
class ZonedDateTimeCodecProvider() : CodecProvider {
    override fun <T : Any?> get(clazz: Class<T>?, codecRegistry: CodecRegistry): Codec<T>? {
        if (clazz == ZonedDateTime::class.java)
            return ZonedDateTimeCodec() as Codec<T>
        return null
    }
}
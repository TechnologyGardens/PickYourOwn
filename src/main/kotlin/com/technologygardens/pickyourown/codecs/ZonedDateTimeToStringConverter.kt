package com.technologygardens.pickyourown.codecs

import org.springframework.core.convert.converter.Converter
import java.time.ZonedDateTime

internal enum class ZonedDateTimeToStringConverter: Converter<ZonedDateTime,String> {
    INSTANCE;
    override fun convert(source: ZonedDateTime): String = source.toString()
}

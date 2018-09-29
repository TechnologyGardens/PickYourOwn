package com.technologygardens.pickyourown.codecs

import org.springframework.core.convert.converter.Converter
import java.time.ZonedDateTime

internal enum class  StringToZonedDataTimeConverter: Converter<String, ZonedDateTime> {
    INSTANCE;
    override fun convert(source: String): ZonedDateTime = ZonedDateTime.parse(source)
}

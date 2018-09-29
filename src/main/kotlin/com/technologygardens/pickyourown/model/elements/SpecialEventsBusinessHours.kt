package com.technologygardens.pickyourown.model.elements

import com.technologygardens.pickyourown.utils.minuteFmt
import com.technologygardens.pickyourown.utils.monthFmt
import java.time.ZonedDateTime
import java.util.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class SpecialEventBusinessHours(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var name: String = "",
        var opensAt: ZonedDateTime = ZonedDateTime.now(),
        var closesAt: ZonedDateTime = ZonedDateTime.now()) {

    fun getDescription(): String {
        val hours = "${opensAt.hour}:${minuteFmt(opensAt.minute)}-${closesAt.hour}:${minuteFmt(closesAt.minute)}"
        if (opensAt.year == closesAt.year)
            if (opensAt.month == closesAt.month)
                if (opensAt.dayOfMonth == closesAt.dayOfMonth)
                    return "$name ${opensAt.dayOfMonth} ${monthFmt(opensAt.month)} ${opensAt.year}, $hours"
                else
                    return "$name ${opensAt.dayOfMonth}-${closesAt.dayOfMonth} ${monthFmt(opensAt.month)} ${opensAt.year}, $hours"
            else
                return "$name ${opensAt.dayOfMonth} ${monthFmt(opensAt.month)} - ${closesAt.dayOfMonth} ${monthFmt(closesAt.month)} ${opensAt.year}, $hours"
        else
            return "${name} ${opensAt.dayOfMonth} ${monthFmt(opensAt.month)} ${opensAt.year} - ${closesAt.dayOfMonth} ${monthFmt(closesAt.month)} ${closesAt.year}, $hours"

    }
}


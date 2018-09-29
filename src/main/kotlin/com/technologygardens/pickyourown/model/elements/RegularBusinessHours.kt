package com.technologygardens.pickyourown.model.elements

import com.technologygardens.pickyourown.utils.minuteFmt
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.DayOfWeek
import java.time.ZonedDateTime
import java.util.*

@Document
data class RegularBusinessHours(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var opensAt: ZonedDateTime = ZonedDateTime.now(),
        var closesAt: ZonedDateTime = ZonedDateTime.now()) {

    var daysOfTheWeek: Int = 0

    constructor(id: String = UUID.randomUUID().toString(), daysOfTheWeek: String, opensAt: ZonedDateTime, closesAt: ZonedDateTime) : this(id, opensAt, closesAt) {
        setDaysOfTheWeekSet(daysOfTheWeek)
    }

    fun setDaysOfTheWeekSet(daysOfWeek: String) {
        val days = BitSet(8)
        if (daysOfWeek.contains("WEEKDAYS", true))
            days.set(0, 5)
        if (daysOfWeek.contains("WEEKEND", true))
            days.set(5, 7)
        for (dayOfWeek in daysOfWeek.split(",", ";", " ").map { it.trim() }) {
            for (i: Int in 0..DAYS_OF_WEEK.size - 1)
                if (dayOfWeek.contains(DAYS_OF_WEEK[i], true)) {
                    days.set(i)
                    break
                }
        }
        daysOfTheWeek = days.toByteArray()[0].toInt()
    }

    fun getDaysOfTheWeekSet(): SortedSet<DayOfWeek> {
        val daysOfWeek = sortedSetOf<DayOfWeek>()
        var mask = 1
        for (index in 1..7) {
            if ((mask and this.daysOfTheWeek) != 0)
                daysOfWeek.add(DayOfWeek.of(index))
            mask = mask shl 1
        }
        return daysOfWeek
    }

    fun setDaysOfTheWeekSet(daysOfTheWeek: SortedSet<DayOfWeek>) {
        val daysOfWeek = BitSet(8)
        for (day in daysOfTheWeek)
            daysOfWeek.set(day.value - 1)
        this.daysOfTheWeek = daysOfWeek.toByteArray()[0].toInt()
    }

    fun getDaysOfTheWeekDescription(): String {
        val sb = StringBuilder()
        val iter = getDaysOfTheWeekSet().iterator()
        if (iter.hasNext()) {
            var firstInSeq = iter.next()
            sb.append(DAYS_OF_WEEK[firstInSeq.value - 1])
            var inSequence = 1
            var day: DayOfWeek? = null
            while (iter.hasNext()) {
                day = iter.next()
                if (firstInSeq.value + inSequence == day.value)
                    inSequence++
                else {
                    if (inSequence > 1) {
                        sb.append("-")
                        sb.append(DAYS_OF_WEEK[firstInSeq.value + inSequence - 2])
                    }
                    firstInSeq = day
                    sb.append(",")
                    sb.append(DAYS_OF_WEEK[firstInSeq.value - 1])
                    day = null
                    inSequence = 1
                }
            }
            if (day != null) {
                if (inSequence > 1) sb.append("-") else sb.append(",")
                sb.append(DAYS_OF_WEEK[day.value - 1])
            }
        }
        return sb.toString()
    }

    fun getHoursDescription(): String {
        return "${opensAt.hour}:${minuteFmt(opensAt.minute)}-${closesAt.hour}:${minuteFmt(closesAt.minute)}"
    }

    companion object {
        val MONDAY = 1
        val TUESDAY = 2
        val WEDNESDAY = 4
        val THURSDAY = 8
        val FRIDAY = 16
        val SATURDAY = 32
        val SUNDAY = 64
        val WEEKDAYS = MONDAY + TUESDAY + WEDNESDAY + THURSDAY + FRIDAY
        val WEEKEND = SATURDAY + SUNDAY
        val LIST_SEPARAOR: String = ","
        val SEQUENCE_SEPARAOR: String = "-"
        val DAYS_OF_WEEK: Array<String> = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    }
}


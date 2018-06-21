package com.technologygardens.pickyourown.model.elements

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.time.Month
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.*
import kotlin.math.exp

class SpecialEventBusinessHoursTest {
    val SPECIAL_EVENT_ID = 100L
    val SPECIAL_EVENT_NAME = "Eniov den"
    val SPECIAL_EVENT_OPENS_AT = ZonedDateTime.parse("2018-05-21T10:00:00+02:00[Europe/Sofia]")
    val SPECIAL_EVENT_CLOSES_AT = ZonedDateTime.parse("2018-05-21T20:00:00+02:00[Europe/Sofia]")

    lateinit var specialEventBusinessHours: SpecialEventBusinessHours

    @Before
    fun setUp() {
        specialEventBusinessHours = SpecialEventBusinessHours(SPECIAL_EVENT_ID, SPECIAL_EVENT_NAME, SPECIAL_EVENT_OPENS_AT, SPECIAL_EVENT_CLOSES_AT)
    }

    @Test
    fun getId() {
        assertEquals(SPECIAL_EVENT_ID, specialEventBusinessHours.id)
    }

    @Test
    fun getDescription() {
        var expected = "Eniov den 21 "+Month.MAY.getDisplayName(TextStyle.FULL, Locale.getDefault())+" 2018, 10:00-20:00"
        assertEquals("Expect event: $expected but found:${specialEventBusinessHours.getDescription()}",expected, specialEventBusinessHours.getDescription())
        specialEventBusinessHours.closesAt =  ZonedDateTime.parse("2018-05-22T20:00:00+02:00[Europe/Sofia]") //next day
        expected = "Eniov den 21-22 "+Month.MAY.getDisplayName(TextStyle.FULL, Locale.getDefault())+" 2018, 10:00-20:00"
        assertEquals("Expect event: $expected but found:${specialEventBusinessHours.getDescription()}",expected, specialEventBusinessHours.getDescription())
        specialEventBusinessHours.closesAt = ZonedDateTime.parse("2018-06-21T20:00:00+02:00[Europe/Sofia]") //next month
        expected = "Eniov den 21 "+Month.MAY.getDisplayName(TextStyle.FULL, Locale.getDefault())+" - 21 "+Month.JUNE.getDisplayName(TextStyle.FULL, Locale.getDefault())+" 2018, 10:00-20:00"
        assertEquals("Expect event: $expected but found:${specialEventBusinessHours.getDescription()}",expected, specialEventBusinessHours.getDescription())
        specialEventBusinessHours.closesAt = ZonedDateTime.parse("2019-06-21T20:00:00+02:00[Europe/Sofia]") //next year
        expected = "Eniov den 21 "+Month.MAY.getDisplayName(TextStyle.FULL, Locale.getDefault())+" 2018 - 21 "+Month.JUNE.getDisplayName(TextStyle.FULL, Locale.getDefault())+" 2019, 10:00-20:00"
        assertEquals("Expect event: $expected but found:${specialEventBusinessHours.getDescription()}",expected, specialEventBusinessHours.getDescription())
    }

    @Test
    fun getName() {
        assertEquals(SPECIAL_EVENT_NAME, specialEventBusinessHours.name)
    }

    @Test
    fun setName() {
        val testName = "new provocative name"
        specialEventBusinessHours.name = testName
        assertEquals(testName, specialEventBusinessHours.name)
    }

    @Test
    fun getOpensAt() {
        assertEquals(SPECIAL_EVENT_OPENS_AT, specialEventBusinessHours.opensAt)
    }

    @Test
    fun setOpensAt() {
        val testOpensAt = ZonedDateTime.now()
        specialEventBusinessHours.opensAt = testOpensAt
        assertEquals(testOpensAt, specialEventBusinessHours.opensAt)
    }

    @Test
    fun getClosesAt() {
        assertEquals(SPECIAL_EVENT_CLOSES_AT, specialEventBusinessHours.closesAt)
    }

    @Test
    fun setClosesAt() {
        val testClosesAt = ZonedDateTime.now()
        specialEventBusinessHours.closesAt = testClosesAt
        assertEquals(testClosesAt, specialEventBusinessHours.closesAt)
    }
}
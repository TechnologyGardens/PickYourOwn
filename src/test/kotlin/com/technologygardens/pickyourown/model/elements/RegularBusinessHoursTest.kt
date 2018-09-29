package com.technologygardens.pickyourown.model.elements

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.time.DayOfWeek
import java.time.LocalDateTime

class RegularBusinessHoursTest {
    val BUSINESS_HOURS_ID = "100L"
    val BUSINESS_HOURS_DAYS_OF_WEEK = "weekdays"
    val BUSINESS_HOURS_OPENS_AT = LocalDateTime.parse("2018-05-21T10:00:00")
    val BUSINESS_HOURS_CLOSES_AT = LocalDateTime.parse("2018-05-21T20:00:00")

    lateinit var businessHours: RegularBusinessHours
    @Before
    fun setUp() {
        businessHours = RegularBusinessHours(BUSINESS_HOURS_ID, BUSINESS_HOURS_DAYS_OF_WEEK, BUSINESS_HOURS_OPENS_AT, BUSINESS_HOURS_CLOSES_AT)
    }

    @Test
    fun getDaysOfTheWeek() {
        assertEquals(RegularBusinessHours.WEEKDAYS, businessHours.daysOfTheWeek)
    }

    @Test
    fun setDaysOfTheWeek() {
        businessHours.daysOfTheWeek = RegularBusinessHours.WEEKDAYS+RegularBusinessHours.SUNDAY
        assertEquals(RegularBusinessHours.WEEKDAYS+RegularBusinessHours.SUNDAY, businessHours.daysOfTheWeek)
    }

    @Test
    fun getDaysOfTheWeekSet() {
        val expected = sortedSetOf(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY)
        assertEquals(expected, businessHours.getDaysOfTheWeekSet())
    }

    @Test
    fun setDaysOfTheWeekSet() {
        val testDaysOfTheWeek = sortedSetOf(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY)
        businessHours.setDaysOfTheWeekSet(testDaysOfTheWeek)
        assertEquals(testDaysOfTheWeek, businessHours.getDaysOfTheWeekSet())
    }

    @Test
    fun setDaysOfTheWeek_fromString() {
        var testDaysOfTheWeek = sortedSetOf(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY)
        businessHours.setDaysOfTheWeekSet("WEDNESDAY,Monday, Tue")
        assertEquals(testDaysOfTheWeek, businessHours.getDaysOfTheWeekSet())

        testDaysOfTheWeek = sortedSetOf(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY)
        businessHours.setDaysOfTheWeekSet("weekdays")
        assertEquals(testDaysOfTheWeek, businessHours.getDaysOfTheWeekSet())

        testDaysOfTheWeek = sortedSetOf(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY, DayOfWeek.SUNDAY)
        businessHours.setDaysOfTheWeekSet(" sun, weekdays, sunday, SUNDAY")
        assertEquals(testDaysOfTheWeek, businessHours.getDaysOfTheWeekSet())

        testDaysOfTheWeek = sortedSetOf(DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,DayOfWeek.SUNDAY)
        businessHours.setDaysOfTheWeekSet("fri;tue,thu,wed,weekend")
        assertEquals(testDaysOfTheWeek, businessHours.getDaysOfTheWeekSet())

        testDaysOfTheWeek = sortedSetOf(DayOfWeek.SATURDAY,DayOfWeek.SUNDAY)
        businessHours.setDaysOfTheWeekSet("weekend and some other days but don't know which ")
        assertEquals(testDaysOfTheWeek, businessHours.getDaysOfTheWeekSet())
    }

    @Test
    fun getDaysOfTheWeekDescription() {
        businessHours.daysOfTheWeek = RegularBusinessHours.MONDAY+RegularBusinessHours.TUESDAY+RegularBusinessHours.WEDNESDAY
        assertEquals("Mon-Wed", businessHours.getDaysOfTheWeekDescription())

        var testDaysOfTheWeek = sortedSetOf(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY)
        businessHours.setDaysOfTheWeekSet(testDaysOfTheWeek)
        assertEquals("Mon-Wed,Sat", businessHours.getDaysOfTheWeekDescription())

        testDaysOfTheWeek = sortedSetOf(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,DayOfWeek.SUNDAY)
        businessHours.setDaysOfTheWeekSet(testDaysOfTheWeek)
        assertEquals("Mon-Tue,Fri-Sun", businessHours.getDaysOfTheWeekDescription())

        testDaysOfTheWeek = sortedSetOf(DayOfWeek.MONDAY,DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,DayOfWeek.SUNDAY)
        businessHours.setDaysOfTheWeekSet(testDaysOfTheWeek)
        assertEquals("Mon,Fri-Sun", businessHours.getDaysOfTheWeekDescription())

        testDaysOfTheWeek = sortedSetOf(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY, DayOfWeek.SUNDAY)
        businessHours.setDaysOfTheWeekSet(testDaysOfTheWeek)
        assertEquals("Mon-Tue,Thu-Fri,Sun", businessHours.getDaysOfTheWeekDescription())
    }

    @Test
    fun getHoursDescription() {
        assertEquals("10:00-20:00",businessHours.getHoursDescription())
    }

    @Test
    fun getId() {
        assertEquals(BUSINESS_HOURS_ID, businessHours.id)
    }

    @Test
    fun getOpensAt() {
        assertEquals(BUSINESS_HOURS_OPENS_AT, businessHours.opensAt)
    }

    @Test
    fun setOpensAt() {
        val testOpensAt = LocalDateTime.now()
        businessHours.opensAt = testOpensAt
        assertEquals(testOpensAt, businessHours.opensAt)
    }

    @Test
    fun getClosesAt() {
        assertEquals(BUSINESS_HOURS_CLOSES_AT, businessHours.closesAt)
    }

    @Test
    fun setClosesAt() {
        val testClosesAt = LocalDateTime.now()
        businessHours.closesAt = testClosesAt
        assertEquals(testClosesAt, businessHours.closesAt)
    }
}
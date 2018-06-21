package com.technologygardens.pickyourown.model.elements

import com.technologygardens.pickyourown.model.Farm
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.ZonedDateTime

class SiteTest {

    val SITE_TEST_ID = 0L
    val SITE_TEST_ADDRESS = "31 Rulla Road"
    val SITE_TEST_CITY = "Sisters Creek"
    val SITE_TEST_STATE_PROVINCE = "Tasmania"
    val SITE_TEST_COUNTRY = "Australia"
    val SITE_TEST_POSTAL_CODE = "7325"
    val SITE_TEST_DIRECTIONS = "Take highway A2 from the airport"
    val SITE_TEST_REGULAR_BUSINESS_HOURS: MutableSet<RegularBusinessHours> = mutableSetOf<RegularBusinessHours>(RegularBusinessHours(daysOfTheWeek = "Tuesday,Thursday", opensAt = ZonedDateTime.parse("2018-05-01T09:00:00+10:00[Australia/Tasmania]"), closesAt = ZonedDateTime.parse("2018-10-01T16:30:00+10:00[Australia/Tasmania]")))
    val SITE_TEST_SPECIAL_EVENT_BUSINES_HOURS: MutableSet<SpecialEventBusinessHours> = mutableSetOf<SpecialEventBusinessHours>(SpecialEventBusinessHours(112L,"Festivale",opensAt = ZonedDateTime.parse("2018-02-08T16:00:00+10:00[Australia/Tasmania]"), closesAt = ZonedDateTime.parse("2018-02-10T02:00:00+10:00[Australia/Tasmania]")))
    val SITE_TEST_SEASON_OPENS = "May"
    val SITE_TEST_SEASON_CLOSES = "June"
    val SITE_TEST_SOCIAL = "https://twitter.com/bill_mollison"
    val SITE_TEST_WEB = "http://www.tagari.com"

    lateinit var site: Site
    lateinit var farm: Farm

    @Before
    fun setUp() {
        site = Site(SITE_TEST_ID, SITE_TEST_ADDRESS, SITE_TEST_CITY, SITE_TEST_STATE_PROVINCE, SITE_TEST_COUNTRY, SITE_TEST_POSTAL_CODE, SITE_TEST_DIRECTIONS, SITE_TEST_REGULAR_BUSINESS_HOURS, SITE_TEST_SPECIAL_EVENT_BUSINES_HOURS, SITE_TEST_SEASON_OPENS, SITE_TEST_SEASON_CLOSES, SITE_TEST_SOCIAL, SITE_TEST_WEB)
        farm = Farm(101L, "Farm 1", "description", site)
    }

    @Test
    fun getBusinessHours() {
        val expected = arrayOf("Tue,Thu: 9:00-16:30","Festivale 8-10 February 2018, 16:00-2:00")
        var i=0
        for (hour in site.getBusinessHours())
        assertEquals(expected[i++],hour)
    }

    @Test
    fun getId() {
        assertEquals(SITE_TEST_ID, site.id)
    }

    @Test
    fun getAddress() {
        assertEquals(SITE_TEST_ADDRESS, site.address)
    }

    @Test
    fun setAddress() {
        val testAddress = "new address"
        site.address = testAddress
        assertEquals(testAddress, site.address)
    }

    @Test
    fun getCity() {
        assertEquals(SITE_TEST_CITY, site.city)
    }

    @Test
    fun setCity() {
        val testCity = "new city"
        site.city = testCity
        assertEquals(testCity, site.city)
    }

    @Test
    fun getStateProvince() {
        assertEquals(SITE_TEST_STATE_PROVINCE, site.stateProvince)
    }

    @Test
    fun setStateProvince() {
        val testStateProvince = "new state / province"
        site.stateProvince = testStateProvince
        assertEquals(testStateProvince, site.stateProvince)
    }

    @Test
    fun getCountry() {
        assertEquals(SITE_TEST_COUNTRY, site.country)
    }

    @Test
    fun setCountry() {
        val testCountry = "Bulgaria"
        site.country = testCountry
        assertEquals(testCountry, site.country)
    }

    @Test
    fun getPostalCode() {
        assertEquals(SITE_TEST_POSTAL_CODE, site.postalCode)
    }

    @Test
    fun setPostalCode() {
        val testPostalCode = "new postal code"
        site.postalCode = testPostalCode
        assertEquals(testPostalCode, site.postalCode)
    }

    @Test
    fun getDirections() {
        assertEquals(SITE_TEST_DIRECTIONS, site.directions)
    }

    @Test
    fun setDirections() {
        val testDirections = "new directions"
        site.directions = testDirections
        assertEquals(testDirections, site.directions)
    }

    @Test
    fun getRegularBusinessHours() {
        assertEquals(SITE_TEST_REGULAR_BUSINESS_HOURS, site.regularBusinessHours)
    }

    @Test
    fun setRegularBusinessHours() {
        val testRegularBusinessHours = mutableSetOf<RegularBusinessHours>(RegularBusinessHours(daysOfTheWeek = "Tuesday,Wednesday,Thursday,Friday", opensAt = ZonedDateTime.parse("2018-05-01T10:00:00+02:00[Europe/Sofia]"), closesAt = ZonedDateTime.parse("2018-10-01T18:00:00+02:00[Europe/Sofia]")))
        site.regularBusinessHours = testRegularBusinessHours
        assertEquals(testRegularBusinessHours, site.regularBusinessHours)
    }

    @Test
    fun getSpecialEventBusinessHours() {
        assertEquals(SITE_TEST_SPECIAL_EVENT_BUSINES_HOURS, site.specialEventBusinessHours)
    }

    @Test
    fun setSpecialEventBusinessHours() {
        val testSpecialEventBusinessHours = mutableSetOf<SpecialEventBusinessHours>(SpecialEventBusinessHours(110L,name = "New Year's Eve", opensAt = ZonedDateTime.parse("2018-12-31T10:00:00+02:00[Europe/Sofia]"), closesAt = ZonedDateTime.parse("2019-01-01T18:00:00+02:00[Europe/Sofia]")))
        site.specialEventBusinessHours = testSpecialEventBusinessHours
        assertEquals(testSpecialEventBusinessHours, site.specialEventBusinessHours)
    }


    @Test
    fun getSeasonOpens() {
        assertEquals(SITE_TEST_SEASON_OPENS, site.seasonOpens)
    }

    @Test
    fun setSeasonOpens() {
        val testSeasonOpens = "opens"
        site.seasonOpens= testSeasonOpens
        assertEquals(testSeasonOpens, site.seasonOpens)
    }

    @Test
    fun getSeasonCloses() {
        assertEquals(SITE_TEST_SEASON_CLOSES, site.seasonCloses)
    }

    @Test
    fun setSeasonCloses() {
        val testSeasonCloses = "closes"
        site.seasonOpens= testSeasonCloses
        assertEquals(testSeasonCloses, site.seasonOpens)
    }

    @Test
    fun getSocial() {
        assertEquals(SITE_TEST_SOCIAL, site.social)
    }

    @Test
    fun setSocial() {
        val testSocial = "social media links"
        site.social= testSocial
        assertEquals(testSocial, site.social)
    }

    @Test
    fun getWeb() {
        assertEquals(SITE_TEST_WEB, site.web)
    }

    @Test
    fun setWeb() {
        val testWeb = "website"
        site.web= testWeb
        assertEquals(testWeb, site.web)
    }

    @Test
    fun getFarm() {
        assertEquals(farm, site.farm)
    }

    @Test
    fun setFarm() {
        val testFarm = Farm(111L,"Test Farm","the newest on the block")
        site.farm= testFarm
        assertEquals(testFarm, site.farm)
    }
}
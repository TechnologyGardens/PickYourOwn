package com.technologygardens.pickyourown.model

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class FarmerTest {
    val FARMER_TEST_ID = "100L"
    val FARMER_TEST_FIRST_NAME = "Bill"
    val FARMER_TEST_LAST_NAME = "Mollison"
    val FARMER_TEST_EMAIL = "sales@tagari.com"
    val FARMER_TEST_SOCIAL = "https://twitter.com/bill_mollison"
    val FARMER_TEST_WEB = "http://www.tagari.com"
    val FARMER_TEST_TELEPHONE = "+61 364450945"

    lateinit var farm1: Farm
    lateinit var farm2: Farm
    lateinit var farmer: Farmer

    @Before
    fun setUp() {
        farmer = Farmer(id = FARMER_TEST_ID, firstName = FARMER_TEST_FIRST_NAME, lastName = FARMER_TEST_LAST_NAME, email = FARMER_TEST_EMAIL, social = FARMER_TEST_SOCIAL, web = FARMER_TEST_WEB, telephone = FARMER_TEST_TELEPHONE)
        farm1 = Farm("101L", "Farm 1",  "description")
        farm2 = Farm("102L", "Farm 2",  "description")
    }

    @Test
    fun addFarmRelationship_Nonexistent() {
        farmer.addFarmRelationship(farm1)
        val farms: List<Farm> = farmer.getFarms()
        assert(farms.contains(farm1))
        assertEquals(farms.size, 1)
        assertEquals(farms.iterator().next(), farm1)
    }

    @Test
    fun addFarmRelationship_Existent() {
        farmer.addFarmRelationship(farm1)
        farmer.addFarmRelationship(farm1)
        val farms: List<Farm> = farmer.getFarms()
        assert(farms.contains(farm1))
        assertEquals(farms.size, 1)
        assertEquals(farms.iterator().next(), farm1)
    }

    @Test
    fun getFarms() {
        farmer.addFarmRelationship(farm1)
        farmer.addFarmRelationship(farm2)
        val farms: List<Farm> = farmer.getFarms()
        assert(farms.contains(farm1))
        assert(farms.contains(farm2))
        assertEquals(farms.size, 2)
    }

    @Test
    fun removeFarmRelationship_Nonexistent() {
        farmer.addFarmRelationship(farm2)
        val sizeBefore = farmer.getFarms().size
        farmer.removeFarmRelationship(farm1)
        val farms: List<Farm> = farmer.getFarms()
        assertFalse(farms.contains(farm1))
        assert(farms.contains(farm2))
        assertEquals(sizeBefore, farms.size)
    }

    @Test
    fun removeFarmRelationship_Existent() {
        farmer.addFarmRelationship(farm1)
        farmer.addFarmRelationship(farm2)
        val sizeBefore = farmer.getFarms().size
        farmer.removeFarmRelationship(farm1)
        val farms: List<Farm> = farmer.getFarms()
        assertFalse("Farm1 was removed and should not be contained in farms", farms.contains(farm1))
        assertTrue("Farm 2 is not removed and should be contained in farms", farms.contains(farm2))
        assertEquals("Number of associated farms should be decreased with one", sizeBefore - 1, farms.size)
    }

    @Test
    fun getId() {
        assertEquals(FARMER_TEST_ID, farmer.id)
    }

    @Test
    fun getName() {
        assertEquals(FARMER_TEST_FIRST_NAME + ' ' + FARMER_TEST_LAST_NAME, farmer.getName())
    }

    @Test
    fun getFirstName() {
        assertEquals(FARMER_TEST_FIRST_NAME, farmer.firstName)
    }

    @Test
    fun setFirstName() {
        val testName = "new test first name"
        farmer.firstName = testName
        assertEquals(testName, farmer.firstName)
    }

    @Test
    fun getLastName() {
        assertEquals(FARMER_TEST_LAST_NAME, farmer.lastName)
    }

    @Test
    fun setLastName() {
        val testName = "new test last name"
        farmer.lastName = testName
        assertEquals(testName, farmer.lastName)
    }

    @Test
    fun getEmail() {
        assertEquals(FARMER_TEST_EMAIL, farmer.email)
    }

    @Test
    fun setEmail() {
        val testEmail = "spmething@server.org"
        farmer.email = testEmail
        assertEquals(testEmail, farmer.email)
    }

    @Test
    fun getSocial() {
        assertEquals(FARMER_TEST_SOCIAL, farmer.social)
    }

    @Test
    fun setSocial() {
        val testSocial = "https://twitter.com/PermacultureMag, https://www.facebook.com/PermacultureMag/, https://www.instagram.com/permaculturemagazine/"
        farmer.social = testSocial
        assertEquals(testSocial, farmer.social)
    }

    @Test
    fun getWeb() {
        assertEquals(FARMER_TEST_WEB, farmer.web)
    }

    @Test
    fun setWeb() {
        val testWebsite = "https://www.permaculture.co.uk/"
        farmer.web = testWebsite
        assertEquals(testWebsite, farmer.web)
    }

    @Test
    fun getTelephone() {
        assertEquals(FARMER_TEST_TELEPHONE, farmer.telephone)
    }

    @Test
    fun setTelephone() {
        val testTelephone = "+44 (0)1730 823311"
        farmer.telephone = testTelephone
        assertEquals("Value should be assigned", testTelephone, farmer.telephone)
    }

    @Test
    fun equals_Reflexive() {
        assertEquals("Object should be equal to itself", farmer, farmer)
    }

    @Test
    fun equals_Symmetric() {
        val sameFarmer = Farmer(id = FARMER_TEST_ID, firstName = "Same", lastName = "Same")
        assertEquals("Two an object A is equal to object B then B should be equal to A", farmer, sameFarmer)
        assertEquals("Two an object B is equal to object A then A should be equal to B", sameFarmer, farmer)
    }

    @Test
    fun equals_Transitive() {
        val sameFarmer = Farmer(id = FARMER_TEST_ID, firstName = "Same", lastName = "Same")
        val sameSameFarmer = Farmer(id = FARMER_TEST_ID, firstName = "Same", lastName = "Same")
        if (farmer.equals(sameFarmer) && sameFarmer.equals(sameSameFarmer))
            assertEquals("If A is equal to B and B is equal to C then A should be equalt to C", farmer, sameSameFarmer)
    }

    @Test
    fun equals_Different() {
        val differentFarmer = Farmer(id = FARMER_TEST_ID + 1, firstName = "Same", lastName = "Same")
        assertNotEquals("If farmers have different Ids they should be different", farmer, differentFarmer)
    }

    @Test
    fun testHashCode_Consistency() {
        assertEquals("object hashcode is not consistent with itself! ", farmer.hashCode(), farmer.hashCode())
    }

    @Test
    fun testHashCode_Equality() {
        val sameFarmer = Farmer(id = FARMER_TEST_ID, firstName = "Same", lastName = "Same")
        assertEquals("if objects are equals, then they should have the same hashcode!", farmer.hashCode(), sameFarmer.hashCode())
    }
}

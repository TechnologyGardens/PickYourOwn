package com.technologygardens.pickyourown.model

import com.technologygardens.pickyourown.model.elements.RegularBusinessHours
import com.technologygardens.pickyourown.model.elements.Site
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime

class FarmTest {
    val FARM_TEST_ID = "1L"
    val FARM_TEST_NAME = "Product One"
    val FARM_TEST_IMAGE = byteArrayOf(0,1,2,3,4,5,6,7,8)
    val FARM_TEST_DESCRIPTION = "Farm Description which is longer than 256 symbols. It does not contain any further information besides that: Farm Description which is longer than 256 symbols"
    val FARM_TEST_BUSINESS_HOURS = mutableSetOf<RegularBusinessHours>(
            RegularBusinessHours("1001L","weekdays", LocalDateTime.parse("2018-05-01T10:00:00.0"), LocalDateTime.parse("2018-05-01T10:00:00")),
            RegularBusinessHours("1002L","weekend", LocalDateTime.parse("2018-05-01T10:00:00.0"), LocalDateTime.parse("2018-05-01T14:00:00")))

    val FARM_TEST_SITE = Site("1003L", address = "1 Some street", city = "Sofia", stateProvince = "Sofia District", country = "Bulgaria", postalCode = "1000", directions = "trun left four times", regularBusinessHours = FARM_TEST_BUSINESS_HOURS, seasonOpens = "May 1", seasonCloses = "Oct 1")

    lateinit var farmer1: Farmer
    lateinit var farmer2: Farmer
    lateinit var product1: Product
    lateinit var product2: Product
    lateinit var farm: Farm

    @Before
    fun setUp() {
        farm = Farm(FARM_TEST_ID, FARM_TEST_NAME, FARM_TEST_IMAGE, FARM_TEST_DESCRIPTION, FARM_TEST_SITE)
        farmer1 = Farmer("2L", firstName = "Bill", lastName = "Mollison", email = "sales@tagari.com", web = "http://www.tagari.com", telephone = "+61 364450945")
        farmer2 = Farmer("3L", firstName = "Hristo", lastName = "Aladjov", email = "hristo.aladjov@technologygardens.com", web = "http://technologygardens.com", telephone = "+359 8888888888")
        product1 = Product("128L", "Product 1")
        product2 = Product("129L", "Product 2")
    }

    @Test
    fun addFarmerRelationship_Nonexistent() {
        farm.addFarmerRelationship(farmer1)
        val farmers: List<Farmer> = farm.getFarmers()
        assert(farmers.contains(farmer1))
        assertEquals(farmers.size, 1)
        assertEquals(farmers.iterator().next(), farmer1)
    }

    @Test
    fun addFarmerRelationship_Existent() {
        farm.addFarmerRelationship(farmer1)
        farm.addFarmerRelationship(farmer1)
        val farmers: List<Farmer> = farm.getFarmers()
        assert(farmers.contains(farmer1))
        assertEquals(farmers.size, 1)
    }

    @Test
    fun addProductRelationship_Nonexistent() {
        farm.addProductRelationship(product1)
        val products: List<Product> = farm.getProducts()
        assert(products.contains(product1))
        assertEquals(products.size, 1)
    }

    @Test
    fun addProductRelationship_Existent() {
        farm.addProductRelationship(product1)
        farm.addProductRelationship(product1)
        val products: List<Product> = farm.getProducts()
        assert(products.contains(product1))
        assertEquals(products.size, 1)
    }

    @Test
    fun getFarmers() {
        val farmers: List<Farmer> = farm.getFarmers()
        assertEquals(farmers.size, 0)
        farm.addFarmerRelationship(farmer1)
        farm.addFarmerRelationship(farmer2)
        assert(farmers.contains(farmer1))
        assert(farmers.contains(farmer2))
        assertEquals(farmers.size, 2)
    }

    @Test
    fun getProducts() {
        val products: List<Product> = farm.getProducts()
        assertEquals(products.size, 0)
        farm.addProductRelationship(product1)
        farm.addProductRelationship(product2)
        assert(products.contains(product1))
        assert(products.contains(product2))
        assertEquals(products.size, 2)
    }

    @Test
    fun removeFarmerRelationship_Nonexistent() {
        farm.addFarmerRelationship(farmer1)
        val sizeBefore = farm.getFarmers().size
        farm.removeFarmerRelationship(farmer2)
        val farmers: List<Farmer> = farm.getFarmers()
        assert(farmers.contains(farmer1))
        assertFalse(farmers.contains(farmer2))
        assertEquals(sizeBefore, farmers.size)
    }

    @Test
    fun removeFarmerRelationship_Existent() {
        farm.addFarmerRelationship(farmer1)
        farm.addFarmerRelationship(farmer2)
        val sizeBefore = farm.getFarmers().size
        farm.removeFarmerRelationship(farmer1)
        val farmers: List<Farmer> = farm.getFarmers()
        assertFalse(farmers.contains(farmer1))
        assert(farmers.contains(farmer2))
        assertEquals(sizeBefore - 1, farmers.size)
    }

    @Test
    fun removeProductRelationship_Nonexistent() {
        farm.addProductRelationship(product1)
        val sizeBefore = farm.getProducts().size
        farm.removeProductRelationship(product2)
        val products: List<Product> = farm.getProducts()
        assert(products.contains(product1))
        assertFalse(products.contains(product2))
        assertEquals(sizeBefore, products.size)
    }

    @Test
    fun removeProductRelationship_Existent() {
        farm.addProductRelationship(product1)
        farm.addProductRelationship(product2)
        val sizeBefore = farm.getProducts().size
        farm.removeProductRelationship(product1)
        val products: List<Product> = farm.getProducts()
        assertFalse(products.contains(product1))
        assert(products.contains(product2))
        assertEquals(sizeBefore - 1, products.size)
    }


    @Test
    fun getId() {
        assertEquals(FARM_TEST_ID, farm.id)
    }

    @Test
    fun getName() {
        assertEquals(FARM_TEST_NAME, farm.name)
    }

    @Test
    fun setName() {
        val testName = "new test name"
        farm.name = testName
        assertEquals(testName, farm.name)
    }
    @Test
    fun getImage() {
        assertEquals(FARM_TEST_IMAGE, farm.image)
    }

    @Test
    fun setImage() {
        val testImage = byteArrayOf(4,3,2,1,0)
        farm.image = testImage
        assertEquals(testImage, farm.image)
    }

    @Test
    fun getDescription() {
        assertEquals(FARM_TEST_DESCRIPTION, farm.description)
    }

    @Test
    fun setDescription() {
        val testDescription = "new test description"
        farm.description = testDescription
        assertEquals(testDescription, farm.description)
    }

    @Test
    fun getSite() {
        assertEquals(FARM_TEST_SITE, farm.site)
    }

    @Test
    fun setSite() {
        val testSite = Site(address = "new address")
        farm.site = testSite
        assertEquals(testSite, farm.site)
    }
}
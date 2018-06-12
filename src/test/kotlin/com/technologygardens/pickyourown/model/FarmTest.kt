package com.technologygardens.pickyourown.model

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class FarmTest {
    val FARM_TEST_ID = 1L
    val FARM_TEST_NAME = "Product One"
    val FARM_TEST_DESCRIPTION = "Farm Description which is longer than 256 symbols. It does not contain any further information besides that: Farm Description which is longer than 256 symbols"
    val FARM_TEST_SITE = Farm.Site(address = "1 Some street", city = "Sofia", stateProvince = "Sofia District", country = "Bulgaria", postalCode = "1000", directions = "trun left four times", hours = "M-F 8-18, S 10-14", seasonOpens = "May 1", seasonCloses = "Oct 1")

    lateinit var farmer1: Farmer
    lateinit var farmer2: Farmer
    lateinit var product1: Product
    lateinit var product2: Product
    lateinit var farm: Farm

    @Before
    fun setUp() {
        farm = Farm(id = FARM_TEST_ID, name = FARM_TEST_NAME, description = FARM_TEST_DESCRIPTION, site = FARM_TEST_SITE)
        farmer1 = Farmer(2L, firstName = "Bill", lastName = "Mollison", email = "sales@tagari.com", web = "http://www.tagari.com", telephone = "+61 364450945")
        farmer2 = Farmer(3L, firstName = "Hristo", lastName = "Aladjov", email = "hristo.aladjov@technologygardens.com", web = "http://technologygardens.com", telephone = "+359 8888888888")
        product1 = Product(128L, "Product 1")
        product2 = Product(129L, "Product 2")
    }

    @Test
    fun addFarmerRelationship_Nonexistent() {
        farm.addFarmerRelationship(farmer1)
        val farmers: Set<Farmer> = farm.getFarmers()
        assert(farmers.contains(farmer1))
        assertEquals(farmers.size, 1)
        assertEquals(farmers.iterator().next(), farmer1)
        assert(farmer1.getFarms().contains(farm))
    }

    @Test
    fun addFarmerRelationship_Existent() {
        farm.addFarmerRelationship(farmer1)
        farm.addFarmerRelationship(farmer1)
        val farmers: Set<Farmer> = farm.getFarmers()
        assert(farmers.contains(farmer1))
        assertEquals(farmers.size, 1)
        assertEquals(farmers.iterator().next(), farmer1)
        assert(farmer1.getFarms().contains(farm))
    }

    @Test
    fun addProductRelationship_Nonexistent() {
        farm.addProductRelationship(product1)
        val products: Set<Product> = farm.getProducts()
        assert(products.contains(product1))
        assertEquals(products.size, 1)
        assertEquals(products.iterator().next(), product1)
        assert(product1.getFarms().contains(farm))
    }

    @Test
    fun addProductRelationship_Existent() {
        farm.addProductRelationship(product1)
        farm.addProductRelationship(product1)
        val products: Set<Product> = farm.getProducts()
        assert(products.contains(product1))
        assertEquals(products.size, 1)
        assertEquals(products.iterator().next(), product1)
        assert(product1.getFarms().contains(farm))
    }

    @Test
    fun getFarmers() {
        val farmers: Set<Farmer> = farm.getFarmers()
        assertEquals(farmers.size, 0)
        farm.addFarmerRelationship(farmer1)
        farm.addFarmerRelationship(farmer2)
        assert(farmers.contains(farmer1))
        assert(farmers.contains(farmer2))
        assertEquals(farmers.size, 2)
    }

    @Test
    fun getProducts() {
        val products: Set<Product> = farm.getProducts()
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
        val farmers: Set<Farmer> = farm.getFarmers()
        assert(farmers.contains(farmer1))
        assertFalse(farmers.contains(farmer2))
        assertEquals(sizeBefore, farmers.size)
        assert(farmer1.getFarms().contains(farm))
        assertFalse(farmer2.getFarms().contains(farm))
    }

    @Test
    fun removeFarmerRelationship_Existent() {
        farm.addFarmerRelationship(farmer1)
        farm.addFarmerRelationship(farmer2)
        val sizeBefore = farm.getFarmers().size
        farm.removeFarmerRelationship(farmer1)
        val farmers: Set<Farmer> = farm.getFarmers()
        assertFalse(farmers.contains(farmer1))
        assert(farmers.contains(farmer2))
        assertEquals(sizeBefore - 1, farmers.size)
        assert(farmer2.getFarms().contains(farm))
        assertFalse(farmer1.getFarms().contains(farm))
    }

    @Test
    fun removeProductRelationship_Nonexistent() {
        farm.addProductRelationship(product1)
        val sizeBefore = farm.getProducts().size
        farm.removeProductRelationship(product2)
        val products: Set<Product> = farm.getProducts()
        assert(products.contains(product1))
        assertFalse(products.contains(product2))
        assertEquals(sizeBefore, products.size)
        assert(product1.getFarms().contains(farm))
        assertFalse(product2.getFarms().contains(farm))
    }

    @Test
    fun removeProductRelationship_Existent() {
        farm.addProductRelationship(product1)
        farm.addProductRelationship(product2)
        val sizeBefore = farm.getProducts().size
        farm.removeProductRelationship(product1)
        val products: Set<Product> = farm.getProducts()
        assertFalse(products.contains(product1))
        assert(products.contains(product2))
        assertEquals(sizeBefore - 1, products.size)
        assert(product2.getFarms().contains(farm))
        assertFalse(product1.getFarms().contains(farm))
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
        val testSite = Farm.Site(address = "new address")
        farm.site = testSite
        assertEquals(testSite, farm.site)
    }
}
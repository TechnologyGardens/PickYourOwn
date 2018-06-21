package com.technologygardens.pickyourown.model

import com.technologygardens.pickyourown.model.elements.Site
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ProductTest {
    val PRODUCT_TEST_ID = 1L
    val PRODUCT_TEST_NAME = "Product One"

    lateinit var category1: Category
    lateinit var category2: Category
    lateinit var farm1: Farm
    lateinit var farm2: Farm
    lateinit var product: Product
    @Before
    fun setUp() {
        product = Product(PRODUCT_TEST_ID, PRODUCT_TEST_NAME)
        category1 = Category(2L, "Category 1")
        category2 = Category(3L, name = "Category 2")
        farm1 = Farm(4L, description = "The biggest", name = "Farm 1", site = Site())
        farm2 = Farm(5L, description = "Organic", name = "Farm 2", site = Site())
    }

    @Test
    fun addFarmRelationship_Nonexistent() {
        product.addFarmRelationship(farm1)
        val farms: Set<Farm> = product.getFarms()
        assert(farms.contains(farm1))
        assertEquals(farms.size, 1)
        assertEquals(farms.iterator().next(), farm1)
        assert(farm1.getProducts().contains(product))
    }

    @Test
    fun addFarmRelationship_Existent() {
        product.addFarmRelationship(farm1)
        product.addFarmRelationship(farm1)
        val farms: Set<Farm> = product.getFarms()
        assert(farms.contains(farm1))
        assertEquals(farms.size, 1)
        assertEquals(farms.iterator().next(), farm1)
        assert(farm1.getProducts().contains(product))
    }

    @Test
    fun addCategoryRelationship_Nonexistent() {
        product.addCategoryRelationship(category1)
        val categories: Set<Category> = product.getCategories()
        assert(categories.contains(category1))
        assertEquals(categories.size, 1)
        assertEquals(categories.iterator().next(), category1)
        assert(category1.getProducts().contains(product))
    }

    @Test
    fun addCategoryRelationship_Existent() {
        product.addCategoryRelationship(category1)
        product.addCategoryRelationship(category1)
        val categories: Set<Category> = product.getCategories()
        assert(categories.contains(category1))
        assertEquals(categories.size, 1)
        assertEquals(categories.iterator().next(), category1)
        assert(category1.getProducts().contains(product))
    }

    @Test
    fun getFarms() {
        product.addFarmRelationship(farm1)
        product.addFarmRelationship(farm2)
        val farms: Set<Farm> = product.getFarms()
        assert(farms.contains(farm1))
        assert(farms.contains(farm2))
        assertEquals(farms.size, 2)
    }

    @Test
    fun getCategories() {
        product.addCategoryRelationship(category1)
        product.addCategoryRelationship(category2)
        val categories: Set<Category> = product.getCategories()
        assert(categories.contains(category1))
        assert(categories.contains(category2))
        assertEquals(categories.size, 2)
    }

    @Test
    fun removeFarmRelationship_Nonexistent() {
        product.addFarmRelationship(farm2)
        val sizeBefore = product.getFarms().size
        product.removeFarmRelationship(farm1)
        val farms: Set<Farm> = product.getFarms()
        assertFalse(farms.contains(farm1))
        assert(farms.contains(farm2))
        assertEquals(sizeBefore, farms.size)
        assert(farm2.getProducts().contains(product))
        assertFalse(farm1.getProducts().contains(product))
   }

    @Test
    fun removeFarmRelationship_Existent() {
        product.addFarmRelationship(farm1)
        product.addFarmRelationship(farm2)
        val sizeBefore = product.getFarms().size
        product.removeFarmRelationship(farm1)
        val farms: Set<Farm> = product.getFarms()
        assertFalse(farms.contains(farm1))
        assert(farms.contains(farm2))
        assertEquals(sizeBefore-1, farms.size)
        assert(farm2.getProducts().contains(product))
        assertFalse(farm1.getProducts().contains(product))
    }

    @Test
    fun removeCategoryRelationship_Nonexistent() {
        product.addCategoryRelationship(category2)
        val sizeBefore = product.getCategories().size
        product.removeCategoryRelationship(category1)
        val categories: Set<Category> = product.getCategories()
        assertFalse(categories.contains(category1))
        assert(categories.contains(category2))
        assertEquals(sizeBefore, categories.size)
        assert(category2.getProducts().contains(product))
        assertFalse(category1.getProducts().contains(product))
    }
    
    @Test
    fun removeCategoryRelationship_Existent() {
        product.addCategoryRelationship(category1)
        product.addCategoryRelationship(category2)
        val sizeBefore = product.getCategories().size
        product.removeCategoryRelationship(category1)
        val categories: Set<Category> = product.getCategories()
        assertFalse(categories.contains(category1))
        assert(categories.contains(category2))
        assertEquals(sizeBefore-1, categories.size)
        assert(category2.getProducts().contains(product))
        assertFalse(category1.getProducts().contains(product))
    }

    @Test
    fun getId() {
        assertEquals(PRODUCT_TEST_ID,product.id)
    }

    @Test
    fun getName() {
        assertEquals(PRODUCT_TEST_NAME,product.name)
    }

    @Test
    fun setName() {
        val testName = "new test name"
        product.name= testName
        assertEquals(testName,product.name)
    }
}
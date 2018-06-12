package com.technologygardens.pickyourown.model

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class CategoryTest {
    lateinit var category: Category
    lateinit var product1: Product
    lateinit var product2: Product
    val CATEGORY_TEST_ID = 65536L
    val CATEGORY_TEST_NAME = "test category"

    @Before
    fun setUp() {
        category = Category(CATEGORY_TEST_ID, CATEGORY_TEST_NAME)
        product1 = Product(128L,"Product 1")
        product2 = Product(129L,"Product 2")
    }

    @Test
    fun addProductRelationship_Nonexistent() {
        category.addProductRelationship(product1)
        val products : Set<Product> = category.getProducts()
        assert(products.contains(product1))
        assertEquals(products.size,1)
        assertEquals(products.iterator().next(),product1)
        assert(product1.getCategories().contains(category))
    }

    @Test
    fun addProductRelationship_Existent() {
        category.addProductRelationship(product1)
        category.addProductRelationship(product1)
        val products : Set<Product> = category.getProducts()
        assert(products.contains(product1))
        assertEquals(products.size,1)
        assertEquals(products.iterator().next(),product1)
        assert(product1.getCategories().contains(category))
    }

    @Test
    fun getProducts() {
        category.addProductRelationship(product1)
        category.addProductRelationship(product2)
        val products : Set<Product> = category.getProducts()
        assert(products.contains(product1))
        assert(products.contains(product2))
        assertEquals(products.size,2)
    }

    @Test
    fun removeProductRelationship_Nonexistent() {
        category.addProductRelationship(product1)
        val sizeBefore = category.getProducts().size
        category.removeProductRelationship(product2)
        val products : Set<Product> = category.getProducts()
        assert(products.contains(product1))
        assertFalse(products.contains(product2))
        assertEquals(sizeBefore,products.size)
        assert(product1.getCategories().contains(category))
        assertFalse(product2.getCategories().contains(category))
    }

    @Test
    fun removeProductRelationship_Existent() {
        category.addProductRelationship(product1)
        category.addProductRelationship(product2)
        val sizeBefore = category.getProducts().size
        category.removeProductRelationship(product1)
        val products : Set<Product> = category.getProducts()
        assertFalse(products.contains(product1))
        assert(products.contains(product2))
        assertEquals(sizeBefore-1,products.size)
        assert(product2.getCategories().contains(category))
        assertFalse(product1.getCategories().contains(category))
    }

    @Test
    fun getId() {
        assertEquals(CATEGORY_TEST_ID,category.id)
    }

    @Test
    fun getName() {
        assertEquals(CATEGORY_TEST_NAME,category.name)
    }

    @Test
    fun setName() {
        val testName = "new test name"
        category.name= testName
        assertEquals(testName,category.name)
    }
}
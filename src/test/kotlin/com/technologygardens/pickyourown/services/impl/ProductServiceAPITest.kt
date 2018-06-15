package com.technologygardens.pickyourown.services.impl

import com.nhaarman.mockito_kotlin.verify
import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.repositories.ProductRepository
import com.technologygardens.pickyourown.services.ProductService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model
import java.util.*

class ProductServiceAPITest {
    @Mock
    lateinit var productRepository: ProductRepository

    @Mock
    lateinit var model: Model
    lateinit var productService: ProductService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productService = ProductServiceAPI(productRepository)
    }

    @Test
    fun getProducts() {
        val products = HashSet<Product>()
        products.add(Product(1L, "Product 1"))
        products.add(Product(2L, "Product 2"))

        Mockito.`when`(productRepository.findAll()).thenReturn(products)

        val result: Iterable<Product> = productService.getProducts()

        Mockito.verify(productRepository, Mockito.times(1)).findAll()
        assertTrue(result.all({ products.contains(it) }))
    }

    @Test
    fun getProductById() {
        val product = Product(1L, "Product 1")
        val productOpt: Optional<Product> = Optional.of(product)

        Mockito.`when`(productRepository.findById(ArgumentMatchers.anyLong())).thenReturn(productOpt)
        val result = productService.getProductById(1L)

        assertNotNull("Product not found by the service!",result)
        assertEquals("Wrong Product returned!",result,product)
        verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong())
    }
}
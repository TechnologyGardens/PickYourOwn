package com.technologygardens.pickyourown.controllers

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.repositories.ProductRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model

class ProductControllerTest {

    @Mock
    lateinit var productRepository: ProductRepository

    @Mock
    lateinit var model: Model
    lateinit var productController: ProductController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productController = ProductController(productRepository = productRepository)
    }

    @Test
    fun getProducts() {
        val products = HashSet<Product>()
        products.add(Product(101L,"Apple"))
        products.add(Product(202L,"Orange"))

        Mockito.`when`(productRepository.findAll()).thenReturn(products)

        val argumentCaptor = argumentCaptor<HashSet<Product>>()

        assertEquals("products",productController.getProducts(model))

        Mockito.verify(productRepository, Mockito.times(1)).findAll()
        Mockito.verify(model, Mockito.times(1)).addAttribute(ArgumentMatchers.eq("products"),argumentCaptor.capture())
        val captured : Set<Product> = argumentCaptor.firstValue
        assertTrue(captured.containsAll(products))
    }}
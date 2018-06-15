package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.services.ProductService
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver

class ProductControllerTest {
    @Mock
    lateinit var productService: ProductService

    @Mock
    lateinit var productController: ProductController

    lateinit var mockMVC: MockMvc

    @Before
    fun setUp() {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/templates/")
        viewResolver.setSuffix(".html")

        MockitoAnnotations.initMocks(this)
        productController = ProductController(productService)

        mockMVC = MockMvcBuilders.standaloneSetup(productController)
                .setViewResolvers(viewResolver)
                .build()
    }

    @Test
    fun getProducts() {
        val products = HashSet<Product>()
        products.add(Product(101L,"Apple"))
        products.add(Product(202L,"Orange"))

        Mockito.`when`(productService.getProducts()).thenReturn(products)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("products"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
        Mockito.verify(productService, Mockito.times(1)).getProducts()

    }

    @Test
    fun getProductById() {
        val product  = Product(1L)
        Mockito.`when`(productService.getProductById(ArgumentMatchers.anyLong())).thenReturn(product)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("product"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"))
        Mockito.verify(productService, Mockito.times(1)).getProductById(ArgumentMatchers.eq(1L))
    }
}

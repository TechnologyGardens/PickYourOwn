package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.services.ProductService
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
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
        products.add(Product("101L", "Apple"))
        products.add(Product("202L", "Orange"))

        Mockito.`when`(productService.getProducts()).thenReturn(products)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("products"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
        Mockito.verify(productService, Mockito.times(1)).getProducts()

    }

    @Test
    fun getProductById() {
        val product = Product("1L")
        Mockito.`when`(productService.getProductById(ArgumentMatchers.anyString())).thenReturn(product)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/products/1L"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("product"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"))
        Mockito.verify(productService, Mockito.times(1)).getProductById(ArgumentMatchers.anyString())
    }

    @Test
    fun getFarmById_NotFound() {
        Mockito.`when`(productService.getProductById(anyString())).thenThrow(NotFoundException::class.java)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/products/1L"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
                .andExpect(MockMvcResultMatchers.view().name("404Error"))

    }

    @Test
    fun newProduct() {
        mockMVC.perform(MockMvcRequestBuilders.get("/v1/products/new"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("product-edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isNewProduct"))
    }

    @Test
    fun saveProduct() {
        val product = Product("3L", "Product 1")
        //val anyProduct = ArgumentMatchers.any(Product::class.java) // always returns null and can not be used
        Mockito.`when`(productService.save(product)).thenReturn(product)
        mockMVC.perform(MockMvcRequestBuilders.post("/v1/product/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "3L")
                .param("name", "Product 1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/v1/products/3L"))
        //  Mockito.verify(productService, Mockito.times(1)).save(any<Product>())
    }

    @Test
    fun updateProduct() {
        val product = Product("1L")
        Mockito.`when`(productService.getProductById(ArgumentMatchers.anyString())).thenReturn(product)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/products/1L/update"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("product-edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isNewProduct"))
        Mockito.verify(productService, Mockito.times(1)).getProductById(ArgumentMatchers.anyString())
    }


    @Test
    fun deleteById() {
        mockMVC.perform(MockMvcRequestBuilders.get("/v1/products/1L/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
                .andExpect(MockMvcResultMatchers.view().name("redirect:/v1/products/"))
        Mockito.verify(productService, Mockito.times(1)).deleteById(anyString())
    }
}

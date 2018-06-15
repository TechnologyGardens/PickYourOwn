package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.services.CategoryService
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver

class CategoryControllerTest {
    @Mock
    lateinit var categoryService: CategoryService

    @Mock
    lateinit var categoryController: CategoryController

    lateinit var mockMVC: MockMvc

    @Before
    fun setUp() {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/templates/")
        viewResolver.setSuffix(".html")

        MockitoAnnotations.initMocks(this)
        categoryController = CategoryController(categoryService)

        mockMVC = MockMvcBuilders.standaloneSetup(categoryController)
                .setViewResolvers(viewResolver)
                .build()
    }

    @Test
    fun getCategories() {
        val categories : HashSet<Category>  = HashSet<Category>()
        categories.add(Category(1L))
        categories.add(Category(2L))
        Mockito.`when`(categoryService.getCategories()).thenReturn(categories)

        mockMVC.perform(get("/v1/categories"))
                .andExpect(status().isOk)
                .andExpect(view().name("categories"))
                .andExpect(model().attributeExists("categories"))

        Mockito.verify(categoryService, Mockito.times(1)).getCategories()
    }

    @Test
    fun getCategoryById() {
        val category  = Category(1L)
        Mockito.`when`(categoryService.getCategoryById(ArgumentMatchers.anyLong())).thenReturn(category)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/categories/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("category"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("category"))
        Mockito.verify(categoryService, Mockito.times(1)).getCategoryById(ArgumentMatchers.eq(1L))
    }
}
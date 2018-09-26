package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.services.CategoryService
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver
import java.util.*

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
        val categories: HashSet<Category> = HashSet<Category>()
        categories.add(Category("1L"))
        categories.add(Category("2L"))
        Mockito.`when`(categoryService.getCategories()).thenReturn(categories)

        mockMVC.perform(get("/v1/categories"))
                .andExpect(status().isOk)
                .andExpect(view().name("categories"))
                .andExpect(model().attributeExists("categories"))

        Mockito.verify(categoryService, Mockito.times(1)).getCategories()
    }

    @Test
    fun getCategoryById() {
        val category = Category("1L")
        Mockito.`when`(categoryService.getCategoryById(ArgumentMatchers.anyString())).thenReturn(category)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/categories/1L"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("category"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("category"))
        Mockito.verify(categoryService, Mockito.times(1)).getCategoryById(ArgumentMatchers.anyString())
    }

    @Test
    fun getCategoryById_NotFound() {
        Mockito.`when`(categoryService.getCategoryById(ArgumentMatchers.anyString())).thenThrow(NotFoundException::class.java)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/categories/1L"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
                .andExpect(view().name("404Error"))
    }


    @Test
    fun newCategory() {
        //val category = Category(3L)
        mockMVC.perform(MockMvcRequestBuilders.get("/v1/categories/new"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("category-edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("category"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isNewCategory"))
    }

    @Test
    fun saveCategory() {
        val category = Category("3L", "Category 1")
        //val anyCategory = ArgumentMatchers.any(Category::class.java) // always returns null and can not be used
        Mockito.`when`(categoryService.save(category)).thenReturn(category)
        mockMVC.perform(MockMvcRequestBuilders.post("/v1/category/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "3L")
                .param("name", "Category 1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/v1/categories/3L"))
        //  Mockito.verify(categoryService, Mockito.times(1)).save(any<Category>())
    }

    @Test
    fun deleteById() {
        mockMVC.perform(MockMvcRequestBuilders.get("/v1/categories/1L/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
                .andExpect(MockMvcResultMatchers.view().name("redirect:/v1/categories/"))
        Mockito.verify(categoryService, Mockito.times(1)).deleteById(ArgumentMatchers.anyString())
    }

    @Test
    fun updateCategory() {
        val category = Category("1L")
        Mockito.`when`(categoryService.getCategoryById(ArgumentMatchers.anyString())).thenReturn(category)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/categories/1L/update"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("category-edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("category"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isNewCategory"))
        Mockito.verify(categoryService, Mockito.times(1)).getCategoryById(ArgumentMatchers.anyString())
    }


}
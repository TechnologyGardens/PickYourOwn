package com.technologygardens.pickyourown.controllers

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.repositories.CategoryRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anySet
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model

class CategoryControllerTest {
    @Mock
    lateinit var categoryRepository: CategoryRepository

    @Mock
    lateinit var model: Model
    lateinit var categoryController: CategoryController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        categoryController = CategoryController(categoryRepository)
    }

    @Test
    fun getCategories() {
        val categories = HashSet<Category>()
        categories.add(Category(1L,"Category 1"))
        categories.add(Category(2L,"Category 2"))

        `when`(categoryRepository.findAll()).thenReturn(categories)

        val argumentCaptor = argumentCaptor<HashSet<Category>>()

        assertEquals("categories",categoryController.getCategories(model))

        verify(categoryRepository,times(1)).findAll()
        verify(model, times(1)).addAttribute(eq("categories"),argumentCaptor.capture())
        val captured : Set<Category> = argumentCaptor.firstValue
        assertTrue(captured.containsAll(categories))
    }
}
package com.technologygardens.pickyourown.services.impl

import com.nhaarman.mockito_kotlin.verify
import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.repositories.CategoryRepository
import com.technologygardens.pickyourown.services.CategoryService
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model
import java.util.*

class CategoryServiceAPITest {
    @Mock
    lateinit var categoryRepository: CategoryRepository

    @Mock
    lateinit var model: Model
    lateinit var categoryService: CategoryService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        categoryService = CategoryServiceAPI(categoryRepository)
    }

    @Test
    fun getCategories() {
        val categories = HashSet<Category>()
        categories.add(Category("1L", "Category 1"))
        categories.add(Category("2L", "Category 2"))

        Mockito.`when`(categoryRepository.findAll()).thenReturn(categories)

        val result: Iterable<Category> = categoryService.getCategories()

        Mockito.verify(categoryRepository, Mockito.times(1)).findAll()
        assertTrue(result.all({ categories.contains(it) }))
    }

    @Test
    fun getCategoryById() {
        val category = Category("1L", "Category 1")
        val categoryOpt: Optional<Category> = Optional.of(category)

        Mockito.`when`(categoryRepository.findById(ArgumentMatchers.anyString())).thenReturn(categoryOpt)
        val result = categoryService.getCategoryById("1L")

        Assert.assertNotNull("Farmer not found by the service!", result)
        assertEquals("Wrong Farmer returned!", result, category)
        verify(categoryRepository, Mockito.times(1)).findById(ArgumentMatchers.anyString())
    }

    @Test(expected = NotFoundException::class)
    fun getCategoryById_NotFound() {
        Mockito.`when`(categoryService.getCategoryById(ArgumentMatchers.anyString())).thenReturn(null)
        categoryService.getCategoryById("1L")
    }

    @Test
    fun save() {
        val category = Category("1L", "Category 1")

        Mockito.`when`(categoryRepository.save(ArgumentMatchers.any(Category::class.java))).thenReturn(category)
        val result = categoryService.save(category)

        Assert.assertNotNull("Category not found by the service!", result)
        assertEquals("Wrong Category returned!", result, category)
        verify(categoryRepository, Mockito.times(1)).save(ArgumentMatchers.any(Category::class.java))
    }

    @Test
    fun deleteById() {
        val id = "1L"

        categoryService.deleteById(id)

        verify(categoryRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyString())
    }

    @Test(expected = NotFoundException::class)
    fun deleteById_NotFound() {
        Mockito.`when`(categoryService.deleteById(ArgumentMatchers.anyString())).thenThrow(NotFoundException::class.java)
        categoryService.deleteById("-1L")
    }

}
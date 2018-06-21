package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Category
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class CategoryRepositoryIT {
    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Before
    fun setUp() {
    }

    @Test
    fun findByName_DataSQL(){
        val category : Optional<Category> = categoryRepository.findByName("vegetables")
        Assert.assertEquals("vegetables", category.get().name)
    }

    @Test
    fun findByName_Bootstrap(){
        val category : Optional<Category> = categoryRepository.findByName("fruits")
        Assert.assertEquals("fruits", category.get().name)
    }

}

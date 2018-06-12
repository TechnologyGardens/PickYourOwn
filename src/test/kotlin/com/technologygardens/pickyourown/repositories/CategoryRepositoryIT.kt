package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Category
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
class CategoryRepositoryIT {
    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Before
    fun setUp() {
    }

    @Test
    fun findByName(){
        val category : Optional<Category> = categoryRepository.findByName("vegetables")
        Assert.assertEquals("vegetables", category.get().name)
    }
}

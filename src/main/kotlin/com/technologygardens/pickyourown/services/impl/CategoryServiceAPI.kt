package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.repositories.CategoryRepository
import com.technologygardens.pickyourown.services.CategoryService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class CategoryServiceAPI(private val categoryRepository: CategoryRepository) : CategoryService {

    override fun getCategories(): Iterable<Category> = categoryRepository.findAll()

    override fun getCategoryById(id: String): Category {
        val categoryOpt: Optional<Category> = categoryRepository.findById(id)
        if (!categoryOpt.isPresent)
            throw NotFoundException("Category with Id=${id} not found!")
        return categoryOpt.get()
    }

    override fun save(category: Category): Category {
        logger.debug("Save new farm ${category.name} ($category)")
        return this.categoryRepository.save(category)
    }

    override fun deleteById(id: String) {
        FarmServiceAPI.logger.debug("Delete farm ${id}")
        try {
            this.categoryRepository.deleteById(id)
        } catch (e: Exception) {
            throw NotFoundException("Category with Id=${id} not found! Can not delete non existing category")
        }
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

}
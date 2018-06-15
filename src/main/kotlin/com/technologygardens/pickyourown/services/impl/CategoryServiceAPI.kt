package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.repositories.CategoryRepository
import com.technologygardens.pickyourown.services.CategoryService
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryServiceAPI(private val categoryRepository: CategoryRepository) : CategoryService {

    override fun getCategories(): Iterable<Category> = categoryRepository.findAll()

    override fun getCategoryById(id: Long): Category {
        val categoryOpt: Optional<Category> = categoryRepository.findById(id)
        if (!categoryOpt.isPresent)
            throw NotFoundException("Farmer with Id=${id} not found!")
        return categoryOpt.get()
    }

}
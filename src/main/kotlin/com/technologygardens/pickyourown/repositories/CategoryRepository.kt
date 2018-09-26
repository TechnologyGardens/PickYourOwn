package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : CrudRepository<Category, String> {
    fun findByName(name : String) : Optional<Category>
}
package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : CrudRepository<Category, Long> {
}
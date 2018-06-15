package com.technologygardens.pickyourown.services

import com.technologygardens.pickyourown.model.Category

interface CategoryService{
    fun getCategories(): Iterable<Category>
    fun getCategoryById(id: Long): Category
}
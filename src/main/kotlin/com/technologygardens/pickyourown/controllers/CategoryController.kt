package com.technologygardens.pickyourown.controllers

import com.technologygardens.pickyourown.repositories.CategoryRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class CategoryController {
    private val categoryRepository: CategoryRepository

    constructor(categoryRepository: CategoryRepository) {
        this.categoryRepository = categoryRepository
    }

    @RequestMapping("/v1/categories")
    fun getCategories(model: Model): String {
        model.addAttribute("categories", categoryRepository.findAll())
        return "categories"
    }
}
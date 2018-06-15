package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.services.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class CategoryController(private val categoryService: CategoryService) {

    @RequestMapping("/v1/categories")
    fun getCategories(model: Model): String {
        model.addAttribute("categories", categoryService.getCategories())
        return "categories"
    }

    @RequestMapping("/v1/categories/{id}")
    fun getFarmById(@PathVariable id: String, model: Model): String {
        model.addAttribute("category", categoryService.getCategoryById(id.toLong()))
        return "category"
    }

}
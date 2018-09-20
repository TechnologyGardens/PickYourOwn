package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.services.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
class CategoryController(private val categoryService: CategoryService) {

    @RequestMapping("/v1/categories")
    fun getCategories(model: Model): String {
        model.addAttribute("categories", categoryService.getCategories())
        return "categories"
    }

    @RequestMapping("/v1/categories/{id}")
    fun getCategoryById(@PathVariable id: String, model: Model): String {
        model.addAttribute("category", categoryService.getCategoryById(id.toLong()))
        return "category"
    }

    @GetMapping("/v1/categories/new")
    fun newCategory(model: Model): String {
        model.addAttribute("category", Category())
        model.addAttribute("isNewCategory", true)
        return "category-edit"
    }

    @PostMapping("/v1/category/")
    fun saveCategory(@ModelAttribute("category") category: Category): String {
        println("saveCategory:$category")
        categoryService.save(category)
        return "redirect:/v1/categories/${category.id}"
    }

    @GetMapping("/v1/categories/{id}/update")
    fun updateCategory(@PathVariable id: String, model: Model): String {
        model.addAttribute("category", categoryService.getCategoryById(id.toLong()))
        model.addAttribute("isNewCategory", false)
        return "category-edit"
    }

    @GetMapping("/v1/categories/{id}/delete")
    fun deleteById(@PathVariable id: String): String {
        categoryService.deleteById(id.toLong())
        return "redirect:/v1/categories/"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun identifierNotFound(exception: NotFoundException): ModelAndView{
        val modelAndView = ModelAndView("404Error")
        modelAndView.addObject("context", "Category")
        modelAndView.addObject("exception", exception)
        return  modelAndView
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException::class)
    fun badRequest(exception: NumberFormatException): ModelAndView{
        val modelAndView = ModelAndView("400Error")
        modelAndView.addObject("context", "Category")
        modelAndView.addObject("exception", exception)
        return  modelAndView
    }
}
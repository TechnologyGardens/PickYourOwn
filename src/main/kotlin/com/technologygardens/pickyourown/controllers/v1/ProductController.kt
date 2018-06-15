package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.repositories.ProductRepository
import com.technologygardens.pickyourown.services.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ProductController(private val productService: ProductService) {
    @RequestMapping("/v1/products")
    fun getProducts(model: Model): String {
        model.addAttribute("products", productService.getProducts())
        return "products"
    }

    @RequestMapping("/v1/products/{id}")
    fun getFarmById(@PathVariable id: String, model: Model): String {
        model.addAttribute("product", productService.getProductById(id.toLong()))
        return "product"
    }
}
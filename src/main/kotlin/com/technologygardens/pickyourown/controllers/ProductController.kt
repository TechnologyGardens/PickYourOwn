package com.technologygardens.pickyourown.controllers

import com.technologygardens.pickyourown.repositories.ProductRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ProductController {
    private val productRepository:ProductRepository

    constructor(productRepository: ProductRepository) {
        this.productRepository = productRepository
    }
    @RequestMapping("/v1/products")
    fun getProducts(model:Model) :String
    {
        model.addAttribute("products",productRepository.findAll())
        return "products"
    }
}
package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
class ProductController(private val productService: ProductService) {
    @GetMapping("/v1/products")
    fun getProducts(model: Model): String {
        model.addAttribute("products", productService.getProducts())
        return "products"
    }

    @GetMapping("/v1/products/{id}")
    fun getProductById(@PathVariable id: String, model: Model): String {
        model.addAttribute("product", productService.getProductById(id))
        return "product"
    }

    @GetMapping("/v1/products/new")
    fun newProduct(model: Model): String {
        model.addAttribute("product", Product())
        model.addAttribute("isNewProduct", true)
        return "product-edit"
    }


    @PostMapping("/v1/product/")
    fun saveProduct(@ModelAttribute("product") product: Product): String {
        println("saveProduct:$product")
        productService.save(product)
        return "redirect:/v1/products/${product.id}"
    }

    @GetMapping("/v1/products/{id}/update")
    fun updateProduct(@PathVariable id: String, model: Model): String {
        model.addAttribute("product", productService.getProductById(id))
        model.addAttribute("isNewProduct", false)
        return "product-edit"
    }

    @GetMapping("/v1/products/{id}/delete")
    fun deleteById(@PathVariable id: String): String {
        productService.deleteById(id)
        return "redirect:/v1/products/"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun identifierNotFound(exception: NotFoundException): ModelAndView{
        val modelAndView = ModelAndView("404Error")
        modelAndView.addObject("context", "Product")
        modelAndView.addObject("exception", exception)
        return  modelAndView
    }

}
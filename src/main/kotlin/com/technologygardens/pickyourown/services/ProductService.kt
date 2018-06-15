package com.technologygardens.pickyourown.services

import com.technologygardens.pickyourown.model.Product

interface ProductService {
    fun getProducts(): Iterable<Product>
    fun getProductById(id: Long): Product
}
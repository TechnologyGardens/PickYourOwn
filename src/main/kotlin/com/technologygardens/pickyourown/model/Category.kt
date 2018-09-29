package com.technologygardens.pickyourown.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import kotlin.collections.ArrayList

@Document
data class Category(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var name: String = ""
) {
    @DBRef
    private var products: MutableList<Product> = ArrayList<Product>()

    fun addProductRelationship(product: Product) {
        if (!products.contains(product)) {
            products.add(product)
//            product.addCategoryRelationship(this)
            logger.debug("Add Product ${this.id} (${this.name}) Product ${product.id} (${product.name}) Relationship")
        }
    }

    fun getProducts(): List<Product> = products

    fun removeProductRelationship(product: Product) {
        if (products.contains(product)) {
            products.remove(product)
//            product.removeCategoryRelationship(this)
            logger.debug("Remove Product ${this.id} (${this.name}) Product ${product.id} (${product.name}) Relationship")
        }
    }

    private companion object {
        val logger : Logger = LoggerFactory.getLogger(this::class.java)
    }

}
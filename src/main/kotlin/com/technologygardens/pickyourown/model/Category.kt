package com.technologygardens.pickyourown.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.persistence.*

@Entity
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        var name: String = ""
) {
    @ManyToMany(mappedBy = "categories")
    private var products: MutableSet<Product> = HashSet<Product>()

    fun addProductRelationship(product: Product) {
        if (!products.contains(product)) {
            products.add(product)
            product.addCategoryRelationship(this)
            logger.debug("Add Product ${this.id} (${this.name}) Product ${product.id} (${product.name}) Relationship")
        }
    }

    fun getProducts(): Set<Product> = products

    fun removeProductRelationship(product: Product) {
        if (products.contains(product)) {
            products.remove(product)
            product.removeCategoryRelationship(this)
            logger.debug("Remove Product ${this.id} (${this.name}) Product ${product.id} (${product.name}) Relationship")
        }
    }

    private companion object {
        val logger : Logger = LoggerFactory.getLogger(this::class.java)
    }

}
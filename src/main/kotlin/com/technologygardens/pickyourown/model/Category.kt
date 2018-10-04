package com.technologygardens.pickyourown.model

import org.hibernate.annotations.GenericGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.persistence.*

@Entity
data class Category(
        @Id
        @GeneratedValue(generator = "db-uuid")
        @GenericGenerator(name="db-uuid", strategy = "uuid")
        val id: String = "",
        var name: String = ""
) {
    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
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
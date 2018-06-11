package com.technologygardens.pickyourown.model

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
        }
    }

    fun getProducts(): Set<Product> = products

    fun removeProductRelationship(product: Product) {
        if (products.contains(product)) {
            products.remove(product)
            product.removeCategoryRelationship(this)
        }
    }
}
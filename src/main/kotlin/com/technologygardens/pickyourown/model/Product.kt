package com.technologygardens.pickyourown.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.persistence.*

@Entity
class Product(@Id
              @GeneratedValue(strategy = GenerationType.IDENTITY)
              val id: Long = 0L,
              var name: String = ""
) {
    @ManyToMany
    @JoinTable(name = "farm_products", joinColumns = arrayOf(JoinColumn(name = "farm_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "product_id")))
    private var farms: MutableSet<Farm> = HashSet<Farm>()

    @ManyToMany
    @JoinTable(name = "category_products", joinColumns = arrayOf(JoinColumn(name = "category_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "product_id")))
    private var categories: MutableSet<Category> = HashSet<Category>()

    fun addFarmRelationship(farm: Farm) {
        if (!farms.contains(farm)) {
            farms.add(farm)
            farm.addProductRelationship(this)
            logger.debug("Add Product ${this.id} (${this.name}) Farm ${farm.id} (${farm.name}) Relationship")
        }
    }

    fun addCategoryRelationship(category: Category) {
        if (!categories.contains(category)) {
            categories.add(category)
            category.addProductRelationship(this)
            logger.debug("Add Product ${this.id} (${this.name}) Category ${category.id} (${category.name}) Relationship")
        }
    }

    fun getFarms(): Set<Farm> = farms
    fun getCategories(): Set<Category> = categories

    fun removeFarmRelationship(farm: Farm) {
        if (farms.contains(farm)) {
            farms.remove(farm)
            farm.removeProductRelationship(this)
            logger.debug("Remove Product ${this.id} (${this.name}) Farm ${farm.id} (${farm.name}) Relationship")
        }
    }

    fun removeCategoryRelationship(category: Category) {
        if (categories.contains(category)) {
            categories.remove(category)
            category.removeProductRelationship(this)
            logger.debug("Remove Product ${this.id} (${this.name}) Category ${category.id} (${category.name}) Relationship")
        }
    }
    private companion object {
        val logger : Logger = LoggerFactory.getLogger(this::class.java)
    }
}
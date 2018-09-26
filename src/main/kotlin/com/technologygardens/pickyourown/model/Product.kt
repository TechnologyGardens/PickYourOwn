package com.technologygardens.pickyourown.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.*

@Entity
class Product(@Id
              val id: String = UUID.randomUUID().toString(),
              var name: String = ""
) {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "farm_products", joinColumns = arrayOf(JoinColumn(name = "farm_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "product_id")))
    private var farms: MutableSet<Farm> = HashSet<Farm>()

    @ManyToMany(fetch = FetchType.EAGER)
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
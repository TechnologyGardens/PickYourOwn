package com.technologygardens.pickyourown.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import kotlin.collections.ArrayList


@Document
class Product(@Id
              val id: String = UUID.randomUUID().toString(),
              var name: String = ""
) {
    @DBRef
    private var farms: MutableList<Farm> = ArrayList<Farm>()
    @DBRef
    private var categories: MutableList<Category> = ArrayList<Category>()

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

    fun getFarms(): List<Farm> = farms
    fun getCategories(): List<Category> = categories

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
package com.technologygardens.pickyourown.model

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
        }
    }

    fun addCategoryRelationship(category: Category) {
        if (!categories.contains(category)) {
            categories.add(category)
            category.addProductRelationship(this)
        }
    }

    fun getFarms(): Set<Farm> = farms
    fun getCategories(): Set<Category> = categories

    fun removeFarmRelationship(farm: Farm) {
        if (farms.contains(farm)) {
            farms.remove(farm)
            farm.removeProductRelationship(this)
        }
    }

    fun removeCategoryRelationship(category: Category) {
        if (categories.contains(category)) {
            categories.remove(category)
            category.removeProductRelationship(this)
        }
    }

}
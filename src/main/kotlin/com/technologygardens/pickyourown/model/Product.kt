package com.technologygardens.pickyourown.model

import javax.persistence.*

@Entity
class Product(@Id
              @GeneratedValue(strategy = GenerationType.AUTO)
              val id: Long = 0L,
              var name: String = "",
              @ManyToMany
              @JoinTable(name="farm_products",joinColumns = arrayOf(JoinColumn(name="farm_id")),inverseJoinColumns = arrayOf(JoinColumn(name="product_id")))
              var farms: MutableSet<Farm> = HashSet<Farm>(),
              @ManyToMany
              @JoinTable(name="category_products",joinColumns = arrayOf(JoinColumn(name="category_id")),inverseJoinColumns = arrayOf(JoinColumn(name="product_id")))
              var categories: MutableSet<Category> = HashSet<Category>()
) {

}
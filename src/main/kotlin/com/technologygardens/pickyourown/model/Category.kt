package com.technologygardens.pickyourown.model

import javax.persistence.*

@Entity
public data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0L,
        var name: String = "",
        @ManyToMany(mappedBy = "categories")
        var products: MutableSet<Product> = HashSet<Product>()
)
package com.technologygardens.pickyourown.model

import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
public data class Farm(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0L,
        var name: String = "",
        //todo add logo of the farm
        //todo add images of the farm
        @Embedded
        var site: Site = Site(),
        @ManyToMany(mappedBy = "farms")
        var farmers: MutableSet<Farmer> = HashSet<Farmer>(),
        @ManyToMany(mappedBy="farms")
        var products: MutableSet<Product> = HashSet<Product>()
) {

    @Embeddable
    public data class Site(
            var address: String = "",
            var city: String = "",
            var country: String = "",
            var postalCode: String = "",
            var directions: String = "",
            var hours: String = "",
            var seasonOpens: String = "",
            var seasonCloses: String = "",
            var social: String = "",
            var web: String = ""
    )
}

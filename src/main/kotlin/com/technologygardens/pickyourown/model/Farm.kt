package com.technologygardens.pickyourown.model

import javax.persistence.*

@Entity
data class Farm(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        var name: String = "",
        //todo add logo of the farm
        //todo add images of the farm
        @Lob
        var description: String = "",
        @Embedded
        var site: Site = Site()
) {
    @ManyToMany(mappedBy = "farms")
    private var farmers: MutableSet<Farmer> = HashSet<Farmer>()

    @ManyToMany(mappedBy = "farms")
    private var products: MutableSet<Product> = HashSet<Product>()

    fun addFarmerRelationship(farmer: Farmer) {
        if (!farmers.contains(farmer)) {
            farmers.add(farmer)
            farmer.addFarmRelationship(this)
        }
    }

    fun addProductRelationship(product: Product) {
        if (!products.contains(product)) {
            products.add(product)
            product.addFarmRelationship(this)
        }
    }

    fun getFarmers(): Set<Farmer> = farmers

    fun getProducts(): Set<Product> = products

    fun removeFarmerRelationship(farmer: Farmer) {
        if (farmers.contains(farmer)) {
            farmers.add(farmer)
            farmer.removeFarmRelationship(this)
        }
    }

    fun removeProductRelationship(product: Product) {
        if (products.contains(product)) {
            products.add(product)
            product.removeFarmRelationship(this)
        }
    }

    @Embeddable
    data class Site(
            var address: String = "",
            var city: String = "",
            var stateProvince: String = "",
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

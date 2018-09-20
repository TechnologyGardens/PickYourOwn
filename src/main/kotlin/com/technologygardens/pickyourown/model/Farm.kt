package com.technologygardens.pickyourown.model

import com.technologygardens.pickyourown.model.elements.Site
import org.hibernate.annotations.Cascade
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.persistence.*

@Entity
open class Farm(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Cascade(org.hibernate.annotations.CascadeType.ALL)
        val id: Long = 0L,
        var name: String = "",
        //todo add logo of the farm
        @Lob
        var image: ByteArray = byteArrayOf(),
        @Lob
        var description: String = ""
) {
    constructor(id: Long,
                name: String,
                image: ByteArray,
                description: String,
                site: Site = Site()) : this(id, name, image, description) {
        this.site = site
    }

    constructor(id: Long,
                name: String,
                description: String) : this(id, name, byteArrayOf(), description)

    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    var site: Site = Site()
        set (value) {
            field = value
            if (field.farm != this)
                field.farm = this
        }

    @ManyToMany(mappedBy = "farms", cascade = arrayOf(CascadeType.ALL))
    private var farmers: MutableSet<Farmer> = HashSet<Farmer>()

    @ManyToMany(mappedBy = "farms", cascade = arrayOf(CascadeType.ALL))
    private var products: MutableSet<Product> = HashSet<Product>()

    fun addFarmerRelationship(farmer: Farmer) {
        if (!farmers.contains(farmer)) {
            farmers.add(farmer)
            farmer.addFarmRelationship(this)
            logger.debug("Add Farm ${this.id} (${this.name}) Farmer ${farmer.id} (${farmer.getName()}) Relationship")

        }
    }

    fun addProductRelationship(product: Product) {
        if (!products.contains(product)) {
            products.add(product)
            product.addFarmRelationship(this)
            logger.debug("Add Farm ${this.id} (${this.name}) Product ${product.id} (${product.name}) Relationship")
        }
    }

    fun getFarmers(): Set<Farmer> = farmers

    fun getProducts(): Set<Product> = products

    fun removeFarmerRelationship(farmer: Farmer) {
        if (farmers.contains(farmer)) {
            farmers.remove(farmer)
            farmer.removeFarmRelationship(this)
            logger.debug("Remove Farm ${this.id} (${this.name}) Farmer ${farmer.id} (${farmer.getName()}) Relationship")
        }
    }

    fun removeProductRelationship(product: Product) {
        if (products.contains(product)) {
            products.remove(product)
            product.removeFarmRelationship(this)
            logger.debug("Remove Farm ${this.id} (${this.name}) Product ${product.id} (${product.name}) Relationship")
        }
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

}

package com.technologygardens.pickyourown.model

import com.technologygardens.pickyourown.model.elements.Site
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
open class Farm(
        @Id
        @GeneratedValue(generator = "db-uuid")
        @GenericGenerator(name="db-uuid", strategy = "uuid")
        @Cascade(org.hibernate.annotations.CascadeType.ALL)
        val id: String = "",
        @field:Size(min=1, max = 255)
        var name: String = "",
        //todo add logo of the farm
        @Lob
        var image: ByteArray = byteArrayOf(),
        @Lob
        @Type(type = "org.hibernate.type.TextType")
        @field:NotBlank
        var description: String = ""
) {
    constructor(id: String,
                name: String,
                image: ByteArray,
                description: String,
                site: Site = Site()) : this(id, name, image, description) {
        this.site = site
    }

    constructor(id: String,
                name: String,
                description: String) : this(id, name, byteArrayOf(), description)

    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @Valid
    var site: Site = Site()
        set (value) {
            field = value
            if (field.farm != this)
                field.farm = this
        }

    @ManyToMany(mappedBy = "farms", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    private var farmers: MutableSet<Farmer> = HashSet<Farmer>()

    @ManyToMany(mappedBy = "farms", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
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

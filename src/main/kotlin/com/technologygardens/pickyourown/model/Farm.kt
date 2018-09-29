package com.technologygardens.pickyourown.model

import com.technologygardens.pickyourown.model.elements.Site
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import kotlin.collections.ArrayList

@Document
open class Farm(
        @Id
        val id: String = UUID.randomUUID().toString(),
        @field:Size(min=1, max = 255)
        var name: String = "",
        //todo add logo of the farm
        var image: ByteArray = byteArrayOf(),
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

    @Valid
    @DBRef
    var site: Site = Site()
        set (value) {
            field = value
            if (field.farm != this)
                field.farm = this
        }
    @DBRef
    private var farmers: MutableList<Farmer> = ArrayList<Farmer>()
    @DBRef
    private var products: MutableList<Product> = ArrayList<Product>()

    fun addFarmerRelationship(farmer: Farmer) {
        if (!farmers.contains(farmer)) {
            farmers.add(farmer)
//            farmer.addFarmRelationship(this)
            logger.debug("Add Farm ${this.id} (${this.name}) Farmer ${farmer.id} (${farmer.getName()}) Relationship")

        }
    }

    fun addProductRelationship(product: Product) {
        if (!products.contains(product)) {
            products.add(product)
//            product.addFarmRelationship(this)
            logger.debug("Add Farm ${this.id} (${this.name}) Product ${product.id} (${product.name}) Relationship")
        }
    }

    fun getFarmers(): List<Farmer> = farmers

    fun getProducts(): List<Product> = products

    fun removeFarmerRelationship(farmer: Farmer) {
        if (farmers.contains(farmer)) {
            farmers.remove(farmer)
//            farmer.removeFarmRelationship(this)
            logger.debug("Remove Farm ${this.id} (${this.name}) Farmer ${farmer.id} (${farmer.getName()}) Relationship")
        }
    }

    fun removeProductRelationship(product: Product) {
        if (products.contains(product)) {
            products.remove(product)
//            product.removeFarmRelationship(this)
            logger.debug("Remove Farm ${this.id} (${this.name}) Product ${product.id} (${product.name}) Relationship")
        }
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

}

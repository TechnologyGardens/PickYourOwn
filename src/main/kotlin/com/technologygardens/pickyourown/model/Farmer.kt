package com.technologygardens.pickyourown.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Farmer(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var firstName: String = "",
        var lastName: String = "",
        //todo add image or avatar
        var email: String = "",
        var social: String = "",
        var web: String = "",
        var telephone: String = "") {
    @DBRef
    private var farms: MutableList<Farm> = ArrayList<Farm>()

    fun addFarmRelationship(farm: Farm) {
        if (!farms.contains(farm)) {
            farms.add(farm)
//            farm.addFarmerRelationship(this)
            logger.debug("Add Farmer ${this.id} (${this.getName()}) Farm ${farm.id} (${farm.name}) Relationship")

        }
    }

    fun getName(): String = """${this.firstName} ${this.lastName}"""

    fun getFarms(): List<Farm> = farms

    fun removeFarmRelationship(farm: Farm) {
        if (farms.contains(farm)) {
            farms.remove(farm)
//            farm.removeFarmerRelationship(this)
            logger.debug("Remove Farmer ${this.id} (${this.getName()}) Farm ${farm.id} (${farm.name}) Relationship")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Farmer

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    private companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

}

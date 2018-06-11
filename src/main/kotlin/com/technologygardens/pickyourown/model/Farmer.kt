package com.technologygardens.pickyourown.model

import java.util.*
import javax.persistence.*

@Entity
class Farmer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        var firstName: String = "",
        var lastName: String = "",
        //todo add image or avatar
        var email: String = "",
        var social: String = "",
        var web: String = "",
        var telephone: String = "")
{

    @ManyToMany
    @JoinTable(name = "farm_farmers", joinColumns = arrayOf(JoinColumn(name = "farmer_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "farm_id")))
    private var farms: MutableSet<Farm> = HashSet<Farm>()

    fun addFarmRelationship(farm: Farm) {
        if (!farms.contains(farm)) {
            farms.add(farm)
            farm.addFarmerRelationship(this)
        }
    }

    fun getFarms() : Set<Farm> = farms

    fun removeFarmRelationship(farm: Farm) {
        if (farms.contains(farm)) {
            farms.remove(farm)
            farm.removeFarmerRelationship(this)
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
}

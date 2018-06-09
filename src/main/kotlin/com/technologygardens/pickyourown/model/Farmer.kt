package com.technologygardens.pickyourown.model

import org.hibernate.mapping.Join
import java.util.*
import javax.persistence.*
import kotlin.collections.HashMap

@Entity
class Farmer(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0L,
        var firstName: String = "",
        var lastName: String = "",
        //todo add image or avatar
        var email: String = "",
        var social: String = "",
        var web: String = "",
        var telephone: String = "",
        @ManyToMany
        @JoinTable(name="farm_farmers",joinColumns = arrayOf(JoinColumn(name = "farmer_id")),inverseJoinColumns = arrayOf(JoinColumn(name="farm_id")))
        var farms: MutableSet<Farm> = HashSet<Farm>()
)
{
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

package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Farmer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FarmerRepository : CrudRepository<Farmer,String> {
    fun findByLastName(lastName: String) : Optional<Farmer>
    fun findByFirstName(firstName: String) : Optional<Farmer>
}
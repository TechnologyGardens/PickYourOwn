package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Farm
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FarmRepository : CrudRepository<Farm,Long>{
    fun findByName(name : String) : Optional<Farm>
}
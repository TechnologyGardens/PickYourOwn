package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Farmer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FarmerRepository : CrudRepository<Farmer,Long> {
}
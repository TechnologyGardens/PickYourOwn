package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository  : CrudRepository<Product, Long> {
    fun findByName(name : String) : Optional<Product>

}
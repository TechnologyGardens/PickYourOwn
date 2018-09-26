package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Price
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PriceRepository : CrudRepository<Price, String> {
    fun findByFarmIdAndProductId(farm_id: String, product_id : String) : Iterable<Price>
    fun findByFarmId(farm_id: String) : Iterable<Price>
    fun findByProductId(product_id: String) : Iterable<Price>
}
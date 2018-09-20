package com.technologygardens.pickyourown.repositories

import com.technologygardens.pickyourown.model.Price
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PriceRepository : CrudRepository<Price, Long> {
    fun findByFarmIdAndProductId(farm_id: Long, product_id : Long) : Iterable<Price>
    fun findByFarmId(farm_id: Long) : Iterable<Price>
    fun findByProductId(product_id: Long) : Iterable<Price>
}
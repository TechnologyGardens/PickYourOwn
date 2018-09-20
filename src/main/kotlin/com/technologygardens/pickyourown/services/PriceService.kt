package com.technologygardens.pickyourown.services

import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.model.Price
import com.technologygardens.pickyourown.model.Product

interface PriceService {
    fun getPrices(farm: Farm,product: Product): Iterable<Price>
    fun getFormattedBasePrice(farm:Farm,product : Product): String
    fun deleteById(id: Long)
    fun deleteByFarmId(farmId: Long): Iterable<Price>
    fun deleteByProductId(productId: Long): Iterable<Price>
}

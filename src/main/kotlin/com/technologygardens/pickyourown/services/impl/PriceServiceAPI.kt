package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.model.Price
import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.repositories.PriceRepository
import com.technologygardens.pickyourown.services.PriceService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PriceServiceAPI(private val priceRepository: PriceRepository) : PriceService {
    override fun getPrices(farm: Farm, product: Product): Iterable<Price> = priceRepository.findByFarmIdAndProductId(farm.id, product.id)
    override fun getFormattedBasePrice(farm: Farm, product: Product): String {
        val prices: Iterable<Price> = priceRepository.findByFarmIdAndProductId(farm.id, product.id)
        if (prices.iterator().hasNext())
            return prices.first().getFormattedPrice()
        else
            return ""
    }

    override fun deleteById(id: String) {
        logger.debug("Delete price  ${id}")
        this.priceRepository.deleteById(id)
    }

    override fun deleteByFarmId(farmId: String): Iterable<Price> {
        val prices: Iterable<Price> = priceRepository.findByFarmId(farmId)
        for (price in prices)
            deleteById(price.id)
        return prices
    }

    override fun deleteByProductId(productId: String): Iterable<Price> {
        val prices: Iterable<Price> = priceRepository.findByProductId(productId)
        for (price in prices)
            deleteById(price.id)
        return prices
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

}
package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.model.Price
import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.repositories.PriceRepository
import com.technologygardens.pickyourown.services.PriceService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model
import java.math.BigDecimal
import java.util.*

class PriceServiceAPITest {
    @Mock
    lateinit var priceRepository: PriceRepository
    lateinit var farm: Farm
    lateinit var product: Product

    @Mock
    lateinit var model: Model
    lateinit var priceService: PriceService

    @Before
    fun setUp() {
        farm = Farm("100L", "Farm 1",  "Wild one")
        product = Product("200L", "apples")
        MockitoAnnotations.initMocks(this)
        priceService = PriceServiceAPI(priceRepository)
    }

    @Test
    fun getPrices() {
        val prices = HashSet<Price>()
        prices.add(Price("1L", farm, product, BigDecimal.valueOf(120, 2), "BGN/kg", 0, 10))
        prices.add(Price("2L", farm, product, BigDecimal.valueOf(100, 2), "BGN/kg", 10, Int.MAX_VALUE))

        Mockito.`when`(priceRepository.findByFarmIdAndProductId(farm.id, product.id)).thenReturn(prices)

        val result: Iterable<Price> = priceService.getPrices(farm, product)

        Mockito.verify(priceRepository, Mockito.times(1)).findByFarmIdAndProductId(farm.id, product.id)
        Assert.assertTrue(result.all({ prices.contains(it) }))
    }

    @Test
    fun getFormattedBasePrice() {
        val basePrice = "(1.20 BGN/kg)"
        val price1 = Price("1L", farm, product, BigDecimal.valueOf(120, 2), "BGN/kg", 0, 10)
        val prices = linkedSetOf<Price>()
        prices.add(price1)
        prices.add(Price("2L", farm, product, BigDecimal.valueOf(100, 2), "BGN/kg", 10, Int.MAX_VALUE))

        Mockito.`when`(priceRepository.findByFarmIdAndProductId(farm.id, product.id)).thenReturn(prices)

        val result: String = priceService.getFormattedBasePrice(farm, product)

        Mockito.verify(priceRepository, Mockito.times(1)).findByFarmIdAndProductId(farm.id, product.id)
        Assert.assertEquals(result, basePrice)
    }

    @Test
    fun getFormattedBasePrice_WhichDoNotExsit() {
        val basePrice = ""
        val prices = HashSet<Price>()

        Mockito.`when`(priceRepository.findByFarmIdAndProductId(farm.id, product.id)).thenReturn(prices)

        val result: String = priceService.getFormattedBasePrice(farm, product)

        Mockito.verify(priceRepository, Mockito.times(1)).findByFarmIdAndProductId(farm.id, product.id)
        Assert.assertEquals(result, basePrice)
    }

    @Test
    fun deleteById() {
        val id = "1L"

        priceService.deleteById(id)

        Mockito.verify(priceRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyString())
    }

    @Test
    fun deleteByFarmId() {
        val prices = ArrayList<Price>()
        val price1 = Price("1L", farm, product, BigDecimal.valueOf(120, 2), "BGN/kg", 0, 10)
        prices.add(price1)

        Mockito.`when`(priceRepository.findByFarmId(farm.id)).thenReturn(prices)

        val result = priceService.deleteByFarmId(farm.id)

        Mockito.verify(priceRepository, Mockito.times(1)).findByFarmId(ArgumentMatchers.anyString())
        Mockito.verify(priceRepository, Mockito.times(prices.size)).deleteById(ArgumentMatchers.anyString())
        Assert.assertEquals(result, prices)

    }

    @Test
    fun deleteByProductId() {
        val prices = ArrayList<Price>()
        val price1 = Price("1L", farm, product, BigDecimal.valueOf(120, 2), "BGN/kg", 0, 10)
        prices.add(price1)

        Mockito.`when`(priceRepository.findByProductId(product.id)).thenReturn(prices)

        val result = priceService.deleteByProductId(product.id)

        Mockito.verify(priceRepository, Mockito.times(1)).findByProductId(ArgumentMatchers.anyString())
        Mockito.verify(priceRepository, Mockito.times(prices.size)).deleteById(ArgumentMatchers.anyString())
        Assert.assertEquals(result, prices)

    }

}
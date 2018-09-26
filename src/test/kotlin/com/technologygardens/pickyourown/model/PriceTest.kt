package com.technologygardens.pickyourown.model

import com.technologygardens.pickyourown.model.elements.Site
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class PriceTest {
    lateinit var price: Price
    lateinit var farm: Farm
    lateinit var product: Product
    val PRICE_TEST_ID = "100L"
    val FARM_TEST_ID = "1L"
    val PRODUCT_TEST_ID = "2L"
    val VALUE_TEST = BigDecimal.valueOf(250,2)
    val UNIT_TEST = "BGN/kg"
    val MIN_QUANTITY = 0
    val MAX_QUANTITY = Int.MAX_VALUE

    @Before
    fun setUp() {
        farm = Farm(FARM_TEST_ID, name ="Farm 1", image = byteArrayOf(), description = "Best Farm", site = Site())
        product = Product(PRODUCT_TEST_ID, "Product 1")
        price = Price(PRICE_TEST_ID, farm, product, VALUE_TEST, UNIT_TEST, MIN_QUANTITY, MAX_QUANTITY)
    }

    @Test
    fun getId() {
        Assert.assertEquals(PRICE_TEST_ID, price.id)
    }
    @Test
    fun getFarm() {
        Assert.assertEquals(FARM_TEST_ID, price.farm.id)
    }

    @Test
    fun getProduct() {
        Assert.assertEquals(PRODUCT_TEST_ID, price.product.id)
    }

    @Test
    fun getValue() {
        Assert.assertEquals(VALUE_TEST, price.value)
    }

    @Test
    fun setValue() {
        val testValue = BigDecimal.valueOf(150,2)
        price.value = testValue
        Assert.assertEquals(testValue, price.value)
    }

    @Test
    fun getMinQuantity() {
        Assert.assertEquals(MIN_QUANTITY, price.minQuantity)
    }

    @Test
    fun setMinQuantity() {
        val testValue = 10
        price.minQuantity = testValue
        Assert.assertEquals(testValue, price.minQuantity)
    }
    @Test
    fun getMaxQuantity() {
        Assert.assertEquals(MAX_QUANTITY, price.maxQuantity)
    }

    @Test
    fun setMaxQuantity() {
        val testValue = 120
        price.maxQuantity = testValue
        Assert.assertEquals(testValue, price.maxQuantity)
    }

}
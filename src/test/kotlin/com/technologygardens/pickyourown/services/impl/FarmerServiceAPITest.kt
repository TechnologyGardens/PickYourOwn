package com.technologygardens.pickyourown.services.impl

import com.nhaarman.mockito_kotlin.verify
import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.repositories.FarmerRepository
import com.technologygardens.pickyourown.services.FarmerService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model
import java.util.*

class FarmerServiceAPITest {
    @Mock
    lateinit var farmerRepository: FarmerRepository

    @Mock
    lateinit var model: Model
    lateinit var farmerService: FarmerService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        farmerService = FarmerServiceAPI(farmerRepository)
    }

    @Test
    fun getFarmers() {
        val farmers = HashSet<Farmer>()
        farmers.add(Farmer(1L, "Farmer 1"))
        farmers.add(Farmer(2L, "Farmer 2"))

        Mockito.`when`(farmerRepository.findAll()).thenReturn(farmers)

        val result: Iterable<Farmer> = farmerService.getFarmers()

        Mockito.verify(farmerRepository, Mockito.times(1)).findAll()
        assertTrue(result.all({ farmers.contains(it) }))
    }

    @Test
    fun getFarmerById() {
        val farmer = Farmer(1L, "Bill", "Mollison")
        val farmerOpt: Optional<Farmer> = Optional.of(farmer)

        Mockito.`when`(farmerRepository.findById(anyLong())).thenReturn(farmerOpt)
        val result = farmerService.getFarmerById(1L)

        assertNotNull("Farmer not found by the service!",result)
        assertEquals("Wrong Farmer returned!",result,farmer)
        verify(farmerRepository,times(1)).findById(anyLong())
    }
}
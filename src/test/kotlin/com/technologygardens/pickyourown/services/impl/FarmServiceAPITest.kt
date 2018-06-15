package com.technologygardens.pickyourown.services.impl

import com.nhaarman.mockito_kotlin.verify
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.repositories.FarmRepository
import com.technologygardens.pickyourown.services.FarmService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model
import java.util.*

class FarmServiceAPITest {
    @Mock
    lateinit var farmRepository: FarmRepository

    @Mock
    lateinit var model: Model
    lateinit var farmService: FarmService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        farmService = FarmServiceAPI(farmRepository)
    }

    @Test
    fun getFarms() {
        val farms = HashSet<Farm>()
        farms.add(Farm(1L,"Farm 1","Permaculture"))
        farms.add(Farm(2L,"Farm 2","Monoculture"))

        Mockito.`when`(farmRepository.findAll()).thenReturn(farms)

        val result: Iterable<Farm> = farmService.getFarms()

        Mockito.verify(farmRepository, Mockito.times(1)).findAll()
        assertTrue(result.all({ farms.contains(it) }))
    }

    @Test
    fun getFarmById() {
        val farm = Farm(1L, "Bunkera")
        val farmOpt: Optional<Farm> = Optional.of(farm)

        Mockito.`when`(farmRepository.findById(ArgumentMatchers.anyLong())).thenReturn(farmOpt)
        val result = farmService.getFarmById(1L)

        assertNotNull("Farm not found by the service!",result)
        assertEquals("Wrong Farm returned!",result,farm)
        verify(farmRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong())
    }
}
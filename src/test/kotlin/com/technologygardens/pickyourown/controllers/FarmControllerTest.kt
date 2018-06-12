package com.technologygardens.pickyourown.controllers

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.repositories.FarmRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model

class FarmControllerTest {

    @Mock
    lateinit var farmRepository: FarmRepository

    @Mock
    lateinit var model: Model
    lateinit var farmController: FarmController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        farmController = FarmController(farmRepository = farmRepository)
    }

    @Test
    fun getFarms() {
        val farms = HashSet<Farm>()
        farms.add(Farm(1L,"Farm 1","Permaculture"))
        farms.add(Farm(2L,"Farm 2","Monoculture"))

        Mockito.`when`(farmRepository.findAll()).thenReturn(farms)

        val argumentCaptor = argumentCaptor<HashSet<Farm>>()

        assertEquals("farms",farmController.getFarms(model))

        Mockito.verify(farmRepository, Mockito.times(1)).findAll()
        Mockito.verify(model, Mockito.times(1)).addAttribute(ArgumentMatchers.eq("farms"),argumentCaptor.capture())
        val captured : Set<Farm> = argumentCaptor.firstValue
        assertTrue(captured.containsAll(farms))
    }}
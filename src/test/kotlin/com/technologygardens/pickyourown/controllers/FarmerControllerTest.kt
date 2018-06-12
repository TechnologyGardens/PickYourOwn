package com.technologygardens.pickyourown.controllers

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.repositories.FarmRepository
import com.technologygardens.pickyourown.repositories.FarmerRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model

class FarmerControllerTest {

    @Mock
    lateinit var farmerRepository: FarmerRepository

    @Mock
    lateinit var model: Model
    lateinit var farmerController: FarmerController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        farmerController = FarmerController(farmerRepository = farmerRepository)
    }

    @Test
    fun getFarmers() {
        val farmers = HashSet<Farmer>()
        farmers.add(Farmer(101L,"Bill","Mollison"))
        farmers.add(Farmer(202L,"Hristo","Aladjov"))

        Mockito.`when`(farmerRepository.findAll()).thenReturn(farmers)

        val argumentCaptor = argumentCaptor<HashSet<Farmer>>()

        assertEquals("farmers",farmerController.getFarmers(model))

        Mockito.verify(farmerRepository, Mockito.times(1)).findAll()
        Mockito.verify(model, Mockito.times(1)).addAttribute(ArgumentMatchers.eq("farmers"),argumentCaptor.capture())
        val captured : Set<Farmer> = argumentCaptor.firstValue
        assertTrue(captured.containsAll(farmers))
    }}
package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.services.FarmService
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver

class FarmControllerTest {
    @Mock
    lateinit var farmService: FarmService

    @Mock
    lateinit var farmController: FarmController

    lateinit var mockMVC: MockMvc

    @Before
    fun setUp() {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/templates/")
        viewResolver.setSuffix(".html")

        MockitoAnnotations.initMocks(this)
        farmController = FarmController(farmService)

        mockMVC = MockMvcBuilders.standaloneSetup(farmController)
                .setViewResolvers(viewResolver)
                .build()
    }

    @Test
    fun getFarms() {
        val farms : HashSet<Farm>  = HashSet<Farm>()
        farms.add(Farm(1L))
        farms.add(Farm(2L))
        Mockito.`when`(farmService.getFarms()).thenReturn(farms)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farms"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farms"))
        Mockito.verify(farmService, Mockito.times(1)).getFarms()

    }

    @Test
    fun getFarmById() {
        val farm  = Farm(1L)
        Mockito.`when`(farmService.getFarmById(anyLong())).thenReturn(farm)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farm"))
        Mockito.verify(farmService, Mockito.times(1)).getFarmById(ArgumentMatchers.eq(1L))
    }
}
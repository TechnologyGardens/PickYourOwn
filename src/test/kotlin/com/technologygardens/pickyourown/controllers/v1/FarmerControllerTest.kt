package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.services.FarmerService
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver

class FarmererControllerTest {
    @Mock
    lateinit var farmerService: FarmerService

    @Mock
    lateinit var farmerController: FarmerController

    lateinit var mockMVC: MockMvc

    @Before
    fun setUp() {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/templates/")
        viewResolver.setSuffix(".html")

        MockitoAnnotations.initMocks(this)
        farmerController = FarmerController(farmerService)

        mockMVC = MockMvcBuilders.standaloneSetup(farmerController)
                .setViewResolvers(viewResolver)
                .build()
    }

    @Test
    fun getFarmers() {
        val farmerers = HashSet<Farmer>()
        farmerers.add(Farmer(101L,"Bill","Mollison"))
        farmerers.add(Farmer(202L,"Hristo","Aladjov"))

        Mockito.`when`(farmerService.getFarmers()).thenReturn(farmerers)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farmers"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farmers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farmers"))
        Mockito.verify(farmerService, Mockito.times(1)).getFarmers()

    }

    @Test
    fun getFarmerById() {
        val farmer  = Farmer(1L)
        Mockito.`when`(farmerService.getFarmerById(ArgumentMatchers.anyLong())).thenReturn(farmer)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farmers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farmer"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farmer"))
        Mockito.verify(farmerService, Mockito.times(1)).getFarmerById(ArgumentMatchers.eq(1L))
    }
}

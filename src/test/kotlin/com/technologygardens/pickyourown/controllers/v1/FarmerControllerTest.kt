package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.services.FarmerService
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
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
        farmerers.add(Farmer("101L","Bill","Mollison"))
        farmerers.add(Farmer("202L","Hristo","Aladjov"))

        Mockito.`when`(farmerService.getFarmers()).thenReturn(farmerers)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farmers"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farmers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farmers"))
        Mockito.verify(farmerService, Mockito.times(1)).getFarmers()

    }

    @Test
    fun getFarmerById() {
        val farmer  = Farmer("101L")
        Mockito.`when`(farmerService.getFarmerById(ArgumentMatchers.anyString())).thenReturn(farmer)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farmers/101L"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farmer"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farmer"))
        Mockito.verify(farmerService, Mockito.times(1)).getFarmerById(ArgumentMatchers.anyString())
    }

    @Test
    fun getFarmById_NotFound(){
        Mockito.`when`(farmerService.getFarmerById(ArgumentMatchers.anyString())).thenThrow(NotFoundException::class.java)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farmers/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
                .andExpect(MockMvcResultMatchers.view().name("404Error"))

    }

    @Test
    fun newFarmer() {
        //val farmer = Farmer(3L)
        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farmers/new"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farmer-edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farmer"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isNewFarmer"))
    }

    @Test
    fun saveFarmer() {
        val farmer = Farmer("303L", "Bill", "Mollison")
        //val anyFarmer = ArgumentMatchers.any(Farmer::class.java) // always returns null and can not be used
        Mockito.`when`(farmerService.save(farmer)).thenReturn(farmer)
        mockMVC.perform(MockMvcRequestBuilders.post("/v1/farmer/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "303L")
                .param("firstName", "Bill")
                .param("lastName", "Mollison"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/v1/farmers/303L"))
        //  Mockito.verify(farmerService, Mockito.times(1)).save(any<Farmer>())
    }

    @Test
    fun updateFarmer() {
        val farmer = Farmer("101L")
        Mockito.`when`(farmerService.getFarmerById(ArgumentMatchers.anyString())).thenReturn(farmer)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farmers/1/update"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farmer-edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farmer"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isNewFarmer"))
        Mockito.verify(farmerService, Mockito.times(1)).getFarmerById(ArgumentMatchers.anyString())
    }


    @Test
    fun deleteById() {
        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farmers/1/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
                .andExpect(MockMvcResultMatchers.view().name("redirect:/v1/farmers/"))
        Mockito.verify(farmerService, Mockito.times(1)).deleteById(ArgumentMatchers.anyString())
    }

}

package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.services.FarmService
import com.technologygardens.pickyourown.services.PriceService
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver

class FarmControllerTest {
    @Mock
    lateinit var farmService: FarmService
    @Mock
    lateinit var priceService: PriceService

    @Mock
    lateinit var farmController: FarmController

    lateinit var mockMVC: MockMvc

    @Before
    fun setUp() {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/templates/")
        viewResolver.setSuffix(".html")

        MockitoAnnotations.initMocks(this)
        farmController = FarmController(farmService, priceService)

        mockMVC = MockMvcBuilders.standaloneSetup(farmController)
                .setViewResolvers(viewResolver)
                .build()
    }

    @Test
    fun getFarms() {
        val farms: HashSet<Farm> = HashSet<Farm>()
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
        val farm = Farm(1L)
        Mockito.`when`(farmService.getFarmById(anyLong())).thenReturn(farm)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms/1"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farm"))
        Mockito.verify(farmService, Mockito.times(1)).getFarmById(ArgumentMatchers.eq(1L))
    }

    @Test
    fun getFarmById_NotFound(){
        Mockito.`when`(farmService.getFarmById(anyLong())).thenThrow(NotFoundException::class.java)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
                .andExpect(MockMvcResultMatchers.view().name("404Error"))

    }

    @Test
    fun getFarmById_BadRequest(){
        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms/abc"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.view().name("400Error"))

    }

    @Test
    fun newFarm() {
        //val farm = Farm(3L)
        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms/new"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farm-edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isNewFarm"))
    }

    @Test
    fun saveFarm() {
        val farm = Farm(3L, "Gecori", byteArrayOf(),"description of the farm")
        Mockito.`when`(farmService.save(farm)).thenReturn(farm)
        mockMVC.perform(MockMvcRequestBuilders.post("/v1/farm/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "3")
                .param("name", "Gecori")
                .param("description", "description of the farm"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/v1/farms/3"))
        Mockito.verify(farmService, Mockito.times(1)).save(anyFarm())
    }

    @Test
    fun saveFarm_ValidationError() {
        mockMVC.perform(MockMvcRequestBuilders.post("/v1/farm/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "3")
                .param("name", "")
                .param("description", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("farm-edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farm"))
        Mockito.verify(farmService, Mockito.times(0)).save(anyFarm())

    }

    @Test
    fun deleteById() {
        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms/1/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
                .andExpect(MockMvcResultMatchers.view().name("redirect:/v1/farms/"))
        Mockito.verify(farmService, Mockito.times(1)).deleteById(anyLong())
    }

    @Test
    fun updateFarm() {
        val farm = Farm(1L)
        Mockito.`when`(farmService.getFarmById(anyLong())).thenReturn(farm)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms/1/update"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("farm-edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("farm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isNewFarm"))
        Mockito.verify(farmService, Mockito.times(1)).getFarmById(ArgumentMatchers.eq(1L))
    }

    fun anyFarm() = Mockito.any(Farm::class.java) ?: Farm()

}
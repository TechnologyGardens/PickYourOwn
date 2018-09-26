package com.technologygardens.pickyourown.controllers.v1

import com.nhaarman.mockito_kotlin.createinstance.createInstance
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.model.elements.Site
import com.technologygardens.pickyourown.services.FarmService
import com.technologygardens.pickyourown.services.ImageService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver


class FarmImageControllerTest {

    @Mock
    lateinit var imageService: ImageService

    @Mock
    lateinit var farmService: FarmService

    @Mock
    lateinit var controller: FarmImageController

    lateinit var mockMVC: MockMvc

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/templates/")
        viewResolver.setSuffix(".html")
        MockitoAnnotations.initMocks(this)
        controller = FarmImageController(imageService, farmService)
        mockMVC = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build()
    }

    @Test
    @Throws(Exception::class)
    fun showUploadFormTest() {
        val farm = Farm("1L")
        Mockito.`when`(farmService.getFarmById(anyString())).thenReturn(farm)

        mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms/1L/image"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(model().attributeExists("farm"))
        Mockito.verify(farmService, Mockito.times(1)).getFarmById(anyString())
    }

    @Test
    @Throws(Exception::class)
    fun handleImagePost() {
        val multipartFile = MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Non empty text file".toByteArray())

        mockMVC.perform(multipart("/v1/farms/1L/image").file(multipartFile))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/v1/farms/1L"))

        Mockito.verify(imageService, Mockito.times(1)).saveFarmImageFile(anyString(), any())
    }

    @Test
    fun renderImageFromDB() {
        val image = "fake image".toByteArray()
        val farm = Farm("1L","Wild Farm", image, "test farm with image", Site())
        Mockito.`when`(farmService.getFarmById(anyString())).thenReturn(farm)
        val response : MockHttpServletResponse = mockMVC.perform(MockMvcRequestBuilders.get("/v1/farms/1L/farmimage"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn().response
        Assert.assertEquals(image.size,response.contentAsByteArray.size)
    }


    inline fun <reified T : Any> any() = Mockito.any(T::class.java) ?: createInstance<T>()
}
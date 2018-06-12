package com.technologygardens.pickyourown.controllers

import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.mockito.MockitoAnnotations
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class IndexControllerTest {
    lateinit var indexController: IndexController

    @Before
    fun setUp() {
        indexController = IndexController()
    }

    @Test
    fun mockMvcTest() {
        val mockMvc: MockMvc = standaloneSetup(indexController).build()
        mockMvc.perform(get("")).andExpect(status().isOk()).andExpect(view().name("index"))
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
       // mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index"))
        mockMvc.perform(get("/default")).andExpect(status().isOk()).andExpect(view().name("index"))
    }

    @Test
    fun getIndexPage() {
        Assert.assertEquals("index", indexController.getIndexPage())
    }
}

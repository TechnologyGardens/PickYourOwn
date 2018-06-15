package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.controllers.v1.IndexController
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.springframework.web.servlet.view.InternalResourceViewResolver

class IndexControllerTest {
    lateinit var indexController: IndexController

    @Before
    fun setUp() {
        indexController = IndexController()
    }

    @Test
    fun mockMvcTest() {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/templates/")
        viewResolver.setSuffix(".html")

        val mockMvc: MockMvc = standaloneSetup(indexController)
                .setViewResolvers(viewResolver)
                .build()
        mockMvc.perform(get("")).andExpect(status().isOk()).andExpect(view().name("index"))
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
        mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("index"))
        mockMvc.perform(get("/default")).andExpect(status().isOk()).andExpect(view().name("index"))
    }

    @Test
    fun getIndexPage() {
        Assert.assertEquals("index", indexController.getIndexPage())
    }
}

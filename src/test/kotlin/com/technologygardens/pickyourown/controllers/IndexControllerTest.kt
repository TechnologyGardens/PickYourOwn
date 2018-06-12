package com.technologygardens.pickyourown.controllers

import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.mockito.MockitoAnnotations

class IndexControllerTest {
    lateinit var indexController: IndexController

    @Before
    fun setUp() {
        indexController = IndexController()
    }

    @Test
    fun getIndexPage() {
        Assert.assertEquals("index", indexController.getIndexPage())
    }
}

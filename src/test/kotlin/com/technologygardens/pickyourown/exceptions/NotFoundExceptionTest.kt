package com.technologygardens.pickyourown.exceptions

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class NotFoundExceptionTest {
    lateinit var notFoundException: NotFoundException
    val NOT_FOUND_MESSAGE = "404 Not Found"
    val NOT_FOUND_CAUSE = Throwable()

    @Before
    fun setUp() {
        notFoundException = NotFoundException()
    }

    @Test
    fun createNotFoundException_withMessage(){
        val nfe = NotFoundException(NOT_FOUND_MESSAGE)
        assertEquals(nfe.message, NOT_FOUND_MESSAGE)
    }

    @Test
    fun createNotFoundException_withMessageAndCause(){
        val nfe = NotFoundException(NOT_FOUND_MESSAGE,NOT_FOUND_CAUSE)
        assertEquals(nfe.message, NOT_FOUND_MESSAGE)
        assertEquals(nfe.cause, NOT_FOUND_CAUSE)
    }

}
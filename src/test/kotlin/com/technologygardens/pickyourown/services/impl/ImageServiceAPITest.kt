package com.technologygardens.pickyourown.services.impl

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.times
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.repositories.FarmRepository
import com.technologygardens.pickyourown.services.ImageService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.mock.web.MockMultipartFile
import java.util.*

class ImageServiceAPITest {
    @Mock
    lateinit var farmRepository: FarmRepository

    @Mock
    lateinit var imageService: ImageService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        imageService = ImageServiceAPI(farmRepository)
    }

    @Test
    fun saveFarmImageFileTest() {
        val farm = Farm(1L)
        val farmOpt = Optional.of(farm)
        val multipartFile = MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Non empty text file".toByteArray())

        Mockito.`when`(farmRepository.findById(anyLong())).thenReturn(farmOpt)

        imageService.saveFarmImageFile(farm.id, multipartFile)

        argumentCaptor<Farm>().apply {
            Mockito.verify(farmRepository, times(1)).save(capture())
            assertEquals(multipartFile.bytes.size, firstValue.image.size)
        }

    }
}
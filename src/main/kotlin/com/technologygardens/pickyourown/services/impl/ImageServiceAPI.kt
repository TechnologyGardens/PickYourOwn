package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.repositories.FarmRepository
import com.technologygardens.pickyourown.services.ImageService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException


@Service
class ImageServiceAPI(private val farmRepository: FarmRepository) : ImageService {
    override fun saveFarmImageFile(farmId: Long, file: MultipartFile) {
        try {
            val farm : Farm = farmRepository.findById(farmId).get()
            farm.image = file.bytes
            logger.debug("Image file uploaded")
            farmRepository.save(farm)
        }
        catch (e: IOException){
            logger.debug("Error uploading the image file uploaded")
        }
    }
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
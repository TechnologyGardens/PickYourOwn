package com.technologygardens.pickyourown.services

import org.springframework.web.multipart.MultipartFile

interface ImageService {
    fun saveFarmImageFile(farmId: String, file: MultipartFile)
}
package com.technologygardens.pickyourown.services

import org.springframework.web.multipart.MultipartFile

interface ImageService {
    fun saveFarmImageFile(farmId: Long, file: MultipartFile)
}
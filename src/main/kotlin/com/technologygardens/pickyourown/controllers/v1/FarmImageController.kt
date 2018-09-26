package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.services.FarmService
import com.technologygardens.pickyourown.services.ImageService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

@Controller
class FarmImageController (private val imageService: ImageService, private val farmService: FarmService) {
    @GetMapping("/v1/farms/{id}/image")
    fun showUploadForm(@PathVariable id: String, model: Model): String {
        model.addAttribute("farm", farmService.getFarmById(id))
        return "farm-image_upload_form"
    }

    @PostMapping("/v1/farms/{id}/image")
    fun handleImagePost(@PathVariable id: String, @RequestParam("imagefile") file: MultipartFile): String {
        imageService.saveFarmImageFile(id, file)
        return "redirect:/v1/farms/$id"
    }

    @GetMapping("/v1/farms/{id}/farmimage")
    fun renderImageFromDB(@PathVariable id: String, response: HttpServletResponse) {
        val farm : Farm = farmService.getFarmById(id)
        response.contentType = "image/jpeg"
        farm.image.inputStream().copyTo(response.outputStream)
    }

}
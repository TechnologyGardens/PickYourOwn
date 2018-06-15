package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.repositories.FarmRepository
import com.technologygardens.pickyourown.services.FarmService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FarmController(private val farmService: FarmService) {

    @RequestMapping("/v1/farms")
    fun getFarms(model: Model): String {
        model.addAttribute("farms", farmService.getFarms())
        return "farms"
    }

    @RequestMapping("/v1/farms/{id}")
    fun getFarmById(@PathVariable id: String, model: Model): String {
        model.addAttribute("farm", farmService.getFarmById(id.toLong()))
        return "farm"
    }

}
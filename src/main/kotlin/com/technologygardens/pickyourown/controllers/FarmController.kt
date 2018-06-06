package com.technologygardens.pickyourown.controllers

import com.technologygardens.pickyourown.repositories.FarmRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FarmController {
    private val farmRepository: FarmRepository

    constructor(farmRepository: FarmRepository) {
        this.farmRepository = farmRepository
    }

    @RequestMapping("/v1/farms")
    fun getFarms(model: Model): String {
        model.addAttribute("farms", farmRepository.findAll())
        return "farms"
    }
}
package com.technologygardens.pickyourown.controllers

import com.technologygardens.pickyourown.repositories.FarmerRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FarmerController {
    private val farmerRepository: FarmerRepository

    constructor(farmerRepository: FarmerRepository) {
        this.farmerRepository = farmerRepository
    }
    @RequestMapping("/v1/farmers")
    fun getFarmers(model: Model): String {
        model.addAttribute("farmers", farmerRepository.findAll())
        return "farmers"
    }
}
package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.repositories.FarmerRepository
import com.technologygardens.pickyourown.services.FarmerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FarmerController(private val farmerService: FarmerService) {
    @RequestMapping("/v1/farmers")
    fun getFarmers(model: Model): String {
        model.addAttribute("farmers", farmerService.getFarmers())
        return "farmers"
    }
    @RequestMapping("/v1/farmers/{id}")
    fun getFarmById(@PathVariable id: String, model: Model): String {
        model.addAttribute("farmer", farmerService.getFarmerById(id.toLong()))
        return "farmer"
    }
}
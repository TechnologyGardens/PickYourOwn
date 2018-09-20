package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.services.FarmerService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
class FarmerController(private val farmerService: FarmerService) {
    @GetMapping("/v1/farmers")
    fun getFarmers(model: Model): String {
        model.addAttribute("farmers", farmerService.getFarmers())
        return "farmers"
    }
    @GetMapping("/v1/farmers/{id}")
    fun getFarmById(@PathVariable id: String, model: Model): String {
        model.addAttribute("farmer", farmerService.getFarmerById(id.toLong()))
        return "farmer"
    }
    
    @GetMapping("/v1/farmers/new")
    fun newFarmer(model: Model): String {
        model.addAttribute("farmer", Farmer())
        model.addAttribute("isNewFarmer", true)
        return "farmer-edit"
    }


    @PostMapping("/v1/farmer/")
    fun saveFarmer(@ModelAttribute("farmer") farmer: Farmer): String {
        println("saveFarmer:$farmer")
        farmerService.save(farmer)
        return "redirect:/v1/farmers/${farmer.id}"
    }

    @GetMapping("/v1/farmers/{id}/update")
    fun updateFarmer(@PathVariable id: String, model: Model): String {
        model.addAttribute("farmer", farmerService.getFarmerById(id.toLong()))
        model.addAttribute("isNewFarmer", false)
        return "farmer-edit"
    }

    @GetMapping("/v1/farmers/{id}/delete")
    fun deleteById(@PathVariable id: String): String {
        farmerService.deleteById(id.toLong())
        return "redirect:/v1/farmers/"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun identifierNotFound(exception: NotFoundException): ModelAndView{
        val modelAndView = ModelAndView("404Error")
        modelAndView.addObject("context", "Farmer")
        modelAndView.addObject("exception", exception)
        return  modelAndView
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException::class)
    fun badRequest(exception: NumberFormatException): ModelAndView{
        val modelAndView = ModelAndView("400Error")
        modelAndView.addObject("context", "Farmer")
        modelAndView.addObject("exception", exception)
        return  modelAndView
    }
}
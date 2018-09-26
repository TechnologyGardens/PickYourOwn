package com.technologygardens.pickyourown.controllers.v1

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.services.FarmService
import com.technologygardens.pickyourown.services.PriceService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

@Controller
class FarmController(private val farmService: FarmService, private val priceService: PriceService) {

    @GetMapping("/v1/farms")
    fun getFarms(model: Model): String {
        model.addAttribute("farms", farmService.getFarms())
        model.addAttribute("priceService", priceService)
        return "farms"
    }

    @GetMapping("/v1/farms/{id}")
    fun getFarmById(@PathVariable id: String, model: Model): String {
        model.addAttribute("farm", farmService.getFarmById(id))
        return "farm"
    }

    @GetMapping("/v1/farms/new")
    fun newFarm(model: Model): String {
        model.addAttribute("farm", Farm())
        model.addAttribute("isNewFarm", true)
        return "farm-edit"
    }


    @PostMapping("/v1/farm/")
    fun saveFarm(@Valid @ModelAttribute("farm") farm: Farm, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            for (error in bindingResult.allErrors)
                System.err.println(error.toString())
            return "farm-edit"
        }
        println("saveFarm:$farm")
        farmService.save(farm)
        return "redirect:/v1/farms/${farm.id}"
    }

    @GetMapping("/v1/farms/{id}/update")
    fun updateFarm(@PathVariable id: String, model: Model): String {
        model.addAttribute("farm", farmService.getFarmById(id))
        model.addAttribute("isNewFarm", false)
        return "farm-edit"
    }

    @GetMapping("/v1/farms/{id}/delete")
    fun deleteById(@PathVariable id: String): String {
        farmService.deleteById(id)
        return "redirect:/v1/farms/"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun identifierNotFound(exception: NotFoundException): ModelAndView {
        val modelAndView = ModelAndView("404Error")
        modelAndView.addObject("context", "Farm")
        modelAndView.addObject("exception", exception)
        return modelAndView
    }
}
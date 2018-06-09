package com.technologygardens.pickyourown.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController{
    @RequestMapping("","/","/index")
    fun getIndexPage():String{
        return "index"
    }
}
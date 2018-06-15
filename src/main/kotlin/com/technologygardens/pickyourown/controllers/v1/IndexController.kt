package com.technologygardens.pickyourown.controllers.v1

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController{
    @RequestMapping("","/","/index","/default")
    fun getIndexPage():String{
        return "index"
    }
}
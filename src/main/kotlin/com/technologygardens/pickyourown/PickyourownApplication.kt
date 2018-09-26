package com.technologygardens.pickyourown

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class PickyourownApplication

fun main(args: Array<String>) {
    runApplication<PickyourownApplication>(*args)
}

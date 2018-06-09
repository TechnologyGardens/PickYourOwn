package com.technologygardens.pickyourown

import com.technologygardens.pickyourown.bootstrap.FakeDataSourceConfig
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class PickyourownApplication

fun main(args: Array<String>) {
    val context: ApplicationContext = runApplication<PickyourownApplication>(*args)
    val fds = context.getBean(FakeDataSourceConfig::class) as FakeDataSourceConfig
    println("#############"+fds.username)
}

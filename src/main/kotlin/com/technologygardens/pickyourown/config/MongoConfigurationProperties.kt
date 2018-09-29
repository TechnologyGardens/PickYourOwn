package com.technologygardens.pickyourown.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Profile("dev-mongo", "prod-mongo")
class MongoConfigurationProperties(        var database: String="",
                                           var host: String="",
                                           var port: Int=0,
                                           var user: String="",
                                           var password: String="") {
}
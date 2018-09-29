package com.technologygardens.pickyourown.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:application-dev-mongo.properties")
@Profile("dev-mongo")
class MongoDevPropConfiguration {
    @Bean
    fun mongoConfigurationProperties(): MongoConfigurationProperties {
        return MongoConfigurationProperties()
    }

}
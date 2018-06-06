package com.technologygardens.pickyourown.config

import com.technologygardens.pickyourown.bootstrap.FakeDataSourceConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@Configuration
@PropertySource("classpath:datasource.properties")
data class PropertyConfig(
        @Value("\${ds.username}")
        var user: String? =null,
        @Value("\${ds.password}")
        var password: String? =null,
        @Value("\${ds.name}")
        var database: String? =null,
        @Value("\${ds.url}")
        var url: String? =null
) {
    @Bean
    fun fakeDataSourceConfig(): FakeDataSourceConfig {
        return FakeDataSourceConfig(this.user, this.password, this.database, this.url)
    }

    @Bean
    fun properties(): PropertySourcesPlaceholderConfigurer {
        return PropertySourcesPlaceholderConfigurer()
    }
}
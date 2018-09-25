package com.technologygardens.pickyourown.bootstrap

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "db-dev")
class FakeDataSourceConfig(
        var username: String? = "",
        var password: String? = "",
        var dbName: String? = "",
        var url: String? = ""
)


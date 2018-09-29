package com.technologygardens.pickyourown.config

import com.mongodb.*
import org.bson.codecs.configuration.CodecRegistries
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.support.ConversionServiceFactoryBean
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.support.DefaultConversionService
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.util.*


@Configuration
@EnableMongoRepositories("com.technologygardens.pickyourown.repositories")
@Profile("dev-mongo", "prod-mongo")
class MongoConfig(val mongo: MongoConfigurationProperties) : AbstractMongoConfiguration() {
    override fun getDatabaseName(): String {
        return mongo.database
    }

    @Bean
    override fun mongoClient(): MongoClient {
        val mongoClientOptions = MongoClientOptions.builder().build()
        val mongoCredential = MongoCredential.createCredential(mongo.user, mongo.database, mongo.password.toCharArray())
        return MongoClient(ServerAddress(mongo.host, mongo.port), mongoCredential, mongoClientOptions)
    }

    @Bean
    @Throws(Exception::class)
    override fun mongoDbFactory(): SimpleMongoDbFactory {
        return SimpleMongoDbFactory(mongoClient(), databaseName)
    }

    @Bean
    @Throws(Exception::class)
    override fun mongoTemplate(): MongoTemplate {
        val mongoTemplate = MongoTemplate(mongoDbFactory())
        mongoTemplate.setWriteConcern(WriteConcern.JOURNALED)
        return mongoTemplate
    }
}
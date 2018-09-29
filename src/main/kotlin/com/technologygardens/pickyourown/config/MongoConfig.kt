package com.technologygardens.pickyourown.config

import com.mongodb.*
import com.technologygardens.pickyourown.codecs.StringToZonedDataTimeConverter
import com.technologygardens.pickyourown.codecs.ZonedDateTimeCodec
import com.technologygardens.pickyourown.codecs.ZonedDateTimeCodecProvider
import com.technologygardens.pickyourown.codecs.ZonedDateTimeToStringConverter
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
class MongoConfig(val mongo : MongoConfigurationProperties) : AbstractMongoConfiguration() {
    override fun getDatabaseName(): String {
        return mongo.database
    }

    @Bean
    fun getConversionService(): ConversionService? {
        val bean = ConversionServiceFactoryBean()
        bean.setConverters(getConverters())
        bean.afterPropertiesSet()
        val cs : DefaultConversionService = DefaultConversionService()
        cs.addConverter(StringToZonedDataTimeConverter.INSTANCE)
        cs.addConverter(ZonedDateTimeToStringConverter.INSTANCE)
        return bean.`object`
    }

    private fun getConverters(): Set<Converter<*, *>> {
        val converters = HashSet<Converter<*, *>>()

        converters.add(StringToZonedDataTimeConverter.INSTANCE)
        converters.add(ZonedDateTimeToStringConverter.INSTANCE)

        return converters
    }
    @Bean
    override fun customConversions(): MongoCustomConversions {
       return MongoCustomConversions(arrayListOf(StringToZonedDataTimeConverter.INSTANCE,ZonedDateTimeToStringConverter.INSTANCE))
    }

    @Bean
    @Throws(Exception::class)
    fun mongoConverter(): MappingMongoConverter {
        val dbRefResolver = DefaultDbRefResolver(mongoFactory)
        val mongoConverter = MappingMongoConverter(dbRefResolver, mongoMappingContext)
        mongoConverter.setCustomConversions(customConversions())
        mongoConverter.afterPropertiesSet()
        return mongoConverter
    }
    @Bean
    override fun mongoClient(): MongoClient {
        val codecRegistry = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(ZonedDateTimeCodec()), CodecRegistries.fromProviders(ZonedDateTimeCodecProvider()), MongoClient.getDefaultCodecRegistry())
        val mongoOptions = MongoClientOptions.builder().codecRegistry(codecRegistry).build()
        val mongoCredential = MongoCredential.createCredential(mongo.user, mongo.database, mongo.password.toCharArray())
        return MongoClient(ServerAddress(mongo.host, mongo.port), mongoCredential, mongoOptions)
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

    @Autowired
    private lateinit var  mongoFactory: MongoDbFactory

    @Autowired
    private lateinit var mongoMappingContext: MongoMappingContext
}
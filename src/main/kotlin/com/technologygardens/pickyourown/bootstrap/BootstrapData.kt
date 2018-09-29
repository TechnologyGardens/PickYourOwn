package com.technologygardens.pickyourown.bootstrap

import com.technologygardens.pickyourown.model.*
import com.technologygardens.pickyourown.model.elements.RegularBusinessHours
import com.technologygardens.pickyourown.model.elements.Site
import com.technologygardens.pickyourown.model.elements.SpecialEventBusinessHours
import com.technologygardens.pickyourown.repositories.*
import com.technologygardens.pickyourown.repositories.elements.RegularBusinessHoursReposiory
import com.technologygardens.pickyourown.repositories.elements.SiteRespository
import com.technologygardens.pickyourown.repositories.elements.SpecialEventBusinessHoursRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime


@Component
@Profile("dev-mongo","dev-redis")
class BootstrapData(private var farmRepository: FarmRepository,
                    private var farmerRepository: FarmerRepository,
                    private var productRepository: ProductRepository,
                    private var categoryRepository: CategoryRepository,
                    private var priceRepository: PriceRepository,
                    private var siteRespository: SiteRespository,
                    private var regularBusinessHoursReposiory: RegularBusinessHoursReposiory,
                    private var specialEventBusinessHoursRepository: SpecialEventBusinessHoursRepository
) : ApplicationListener<ContextRefreshedEvent> {


    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (farmRepository.count() == 0L)
            generateData()
    }

    fun generateData() {

        var bunkera = Farm(name = "Bunkera", image = byteArrayOf(), description = "Bunkera is a small family garden, packed with diverse traditional and exotic produce")
        var zahari = Farm(name = "Zahari Stoyanovo", image = byteArrayOf(), description = "Farm close to the sea")
        bunkera = farmRepository.save(bunkera)
        zahari = farmRepository.save(zahari)


        val regularHours = HashSet<RegularBusinessHours>()
        val weekdays = RegularBusinessHours(daysOfTheWeek = "Tuesday,Wednesday,Thursday,Friday", opensAt = LocalDateTime.parse("2018-05-01T10:00:00"), closesAt = LocalDateTime.parse("2018-10-01T18:00:00"))
        regularHours.add(weekdays)
        val weekend = RegularBusinessHours(daysOfTheWeek = "Weekends", opensAt = LocalDateTime.parse("2018-05-01T10:00:00"), closesAt = LocalDateTime.parse("2018-10-01T20:00:00"))
        regularHours.add(weekend)
        val eniovDen = SpecialEventBusinessHours(name = "Eniov Den", opensAt = LocalDateTime.parse("2018-05-21T10:00:00"), closesAt = LocalDateTime.parse("2018-05-21T22:00:00"))
        val specialEvents: MutableSet<SpecialEventBusinessHours> = mutableSetOf(eniovDen)

        regularBusinessHoursReposiory.save(weekdays)
        regularBusinessHoursReposiory.save(weekend)
        specialEventBusinessHoursRepository.save(eniovDen)

        var bunkeraSite = Site(address = "55 Bistrishko Shose", city = "Sofia", country = "Bulgaria", postalCode = "1000", directions = "After a sharp turn on the road to Bistrica", regularBusinessHours = regularHours, specialEventBusinessHours = specialEvents)
        var zahariSite = Site(city = "Zahari Stoianovo", country = "Bulgaria", directions = "Toward the end of the village")

        bunkeraSite = siteRespository.save(bunkeraSite)
        zahariSite = siteRespository.save(zahariSite)

        bunkera.site = bunkeraSite
        zahari.site = zahariSite

        bunkera = farmRepository.save(bunkera)
        zahari = farmRepository.save(zahari)


        val hristo = Farmer(firstName = "Hristo", lastName = " Aladjov", email = "something@technologygardens.com", telephone = "08887777555", web = "technologygardens.com")
        bunkera.addFarmerRelationship(hristo)
        farmerRepository.save(hristo)
        bunkera = farmRepository.save(bunkera)


        val tsvetan = Farmer(firstName = "Tsvetan", lastName = "Aladjov", email = "something@abv.bg ", telephone = "028620000")
        tsvetan.addFarmRelationship(bunkera)
        farmerRepository.save(tsvetan)
        bunkera = farmRepository.save(bunkera)

        val mladen = Farmer(firstName = "Mladen", lastName = "Aladjov", email = "somethinig@yahoo.com ")
        mladen.addFarmRelationship(bunkera)
        mladen.addFarmRelationship(zahari)
        farmerRepository.save(mladen)
        bunkera = farmRepository.save(bunkera)
        zahari = farmRepository.save(zahari)


        var apples = Product(name = "apples")
        apples.addFarmRelationship(bunkera)
        apples.addFarmRelationship(zahari)
        apples = productRepository.save(apples)

        var strawberries = Product(name = "strawberries")
        strawberries.addFarmRelationship(bunkera)
        strawberries = productRepository.save(strawberries)


        var fruits = Category(name = "fruits")
        fruits = categoryRepository.save(fruits)
        fruits.addProductRelationship(strawberries)
        fruits.addProductRelationship(apples)

        var organic = Category(name = "organic/bio")
        organic = categoryRepository.save(organic)
        organic.addProductRelationship(strawberries)
        organic.addProductRelationship(apples)

        categoryRepository.save(fruits)
        categoryRepository.save(organic)
        categoryRepository.save(Category(name = "dairy"))
        categoryRepository.save(Category(name = "meat"))
        categoryRepository.save(Category(name = "confections"))
        categoryRepository.save(Category(name = "vegetables"))
        categoryRepository.save(Category(name = "cereals (grain, bean and legumes)"))
        apples = productRepository.save(apples)
        strawberries = productRepository.save(strawberries)

        val bunkera_price_1_5kg = Price(farm = bunkera, product = apples, value = BigDecimal.valueOf(150, 2), minQuantity = 0, maxQuantity = 5)
        val bunkera_price_gt5kg = Price(farm = bunkera, product = apples, value = BigDecimal.valueOf(120, 2), minQuantity = 5)
        val bunkera_price = Price(farm = bunkera, product = strawberries, value = BigDecimal.valueOf(200, 2), minQuantity = 5)
        priceRepository.save(bunkera_price_1_5kg)
        priceRepository.save(bunkera_price_gt5kg)
        priceRepository.save(bunkera_price)

        val zahari_price_1_5kg = Price(farm = zahari, product = apples, value = BigDecimal.valueOf(170, 2), minQuantity = 0, maxQuantity = 5)
        val zahari_price_gt5kg = Price(farm = zahari, product = apples, value = BigDecimal.valueOf(150, 2), minQuantity = 5)
        priceRepository.save(zahari_price_1_5kg)
        priceRepository.save(zahari_price_gt5kg)


    }
}
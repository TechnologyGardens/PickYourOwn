package com.technologygardens.pickyourown.bootstrap

import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.model.elements.RegularBusinessHours
import com.technologygardens.pickyourown.model.elements.Site
import com.technologygardens.pickyourown.model.elements.SpecialEventBusinessHours
import com.technologygardens.pickyourown.repositories.CategoryRepository
import com.technologygardens.pickyourown.repositories.FarmRepository
import com.technologygardens.pickyourown.repositories.FarmerRepository
import com.technologygardens.pickyourown.repositories.ProductRepository
import com.technologygardens.pickyourown.repositories.elements.RegularBusinessHoursReposiory
import com.technologygardens.pickyourown.repositories.elements.SiteRespository
import com.technologygardens.pickyourown.repositories.elements.SpecialEventBusinessHoursRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Component
class BootstrapData(private var farmRepository: FarmRepository,
                    private var farmerRepository: FarmerRepository,
                    private var productRepository: ProductRepository,
                    private var categoryRepository: CategoryRepository,
                    private var siteRespository: SiteRespository,
                    private var regularBusinessHoursReposiory: RegularBusinessHoursReposiory,
                    private var specialEventBusinessHoursRepository: SpecialEventBusinessHoursRepository
) : ApplicationListener<ContextRefreshedEvent> {


    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        generateData()
    }

    fun generateData() {
        val bunkera = Farm(name = "Bunkera", description = "Bunkera is a small family garden, packed with diverse traditional and exotic produce")
        val zahari = Farm(name = "Zahari Stoyanovo")
        farmRepository.save(bunkera)
        farmRepository.save(zahari)

        val regularHours: MutableSet<RegularBusinessHours> = HashSet<RegularBusinessHours>()
        val weekdays = RegularBusinessHours(daysOfTheWeek = "Tuesday,Wednesday,Thursday,Friday", opensAt = ZonedDateTime.parse("2018-05-01T10:00:00+02:00[Europe/Sofia]"), closesAt = ZonedDateTime.parse("2018-10-01T18:00:00+02:00[Europe/Sofia]"))
        regularHours.add(weekdays)
        val weekend = RegularBusinessHours(daysOfTheWeek = "Weekends", opensAt = ZonedDateTime.parse("2018-05-01T10:00:00+02:00[Europe/Sofia]"), closesAt = ZonedDateTime.parse("2018-10-01T20:00:00+02:00[Europe/Sofia]"))
        regularHours.add(weekend)
        val eniovDen = SpecialEventBusinessHours(name = "Eniov Den", opensAt = ZonedDateTime.parse("2018-05-21T10:00:00+02:00[Europe/Sofia]"), closesAt = ZonedDateTime.parse("2018-05-21T22:00:00+02:00[Europe/Sofia]"))
        val specialEvents: MutableSet<SpecialEventBusinessHours> = mutableSetOf(eniovDen)
        val bunkera_site = Site(address = "55 Bistrishko Shose", city = "Sofia", country = "Bulgaria", postalCode = "1000", directions = "After a sharp turn on the road to Bistrica", regularBusinessHours = regularHours, specialEventBusinessHours = specialEvents)
        val zahari_site = Site(city = "Zahari Stoianovo", country = "Bulgaria")
        bunkera.site = bunkera_site
        zahari.site = zahari_site

        siteRespository.save(bunkera_site)
        siteRespository.save(zahari_site)

        regularBusinessHoursReposiory.save(weekdays)
        regularBusinessHoursReposiory.save(weekend)
        specialEventBusinessHoursRepository.save(eniovDen)

        farmRepository.save(bunkera)
        farmRepository.save(zahari)


        val hristo = Farmer(firstName = "Hristo", lastName = " Aladjov", email = "something@technologygardens.com", telephone = "08887777555", web = "technologygardens.com")
        bunkera.addFarmerRelationship(hristo)
        farmerRepository.save(hristo)

        val tsvetan = Farmer(firstName = "Tsvetan", lastName = "Aladjov", email = "something@abv.bg ", telephone = "028620000")
        tsvetan.addFarmRelationship(bunkera)
        farmerRepository.save(tsvetan)

        val mladen = Farmer(firstName = "Mladen", lastName = "Aladjov", email = "somethinig@yahoo.com ")
        mladen.addFarmRelationship(bunkera)
        mladen.addFarmRelationship(zahari)
        farmerRepository.save(mladen)

        val apples = Product(name = "apples")
        bunkera.addProductRelationship(apples)
        zahari.addProductRelationship(apples)
        productRepository.save(apples)

        val strawberries = Product(name = "strawberries")
        strawberries.addFarmRelationship(bunkera)
        productRepository.save(strawberries)


        val fruits = Category(name = "fruits")
        fruits.addProductRelationship(strawberries)
        fruits.addProductRelationship(apples)
        categoryRepository.save(fruits)

        val organic = Category(name = "organic/bio")
        organic.addProductRelationship(strawberries)
        organic.addProductRelationship(apples)

        categoryRepository.save(organic)
        productRepository.save(strawberries)
        productRepository.save(apples)

    }
}
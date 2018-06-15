package com.technologygardens.pickyourown.bootstrap

import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.repositories.CategoryRepository
import com.technologygardens.pickyourown.repositories.FarmRepository
import com.technologygardens.pickyourown.repositories.FarmerRepository
import com.technologygardens.pickyourown.repositories.ProductRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class BootstrapData(private var farmRepository: FarmRepository,
                    private var farmerRepository: FarmerRepository,
                    private var productRepository: ProductRepository,
                    private var categoryRepository: CategoryRepository) : ApplicationListener<ContextRefreshedEvent> {


    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        generateData()
    }

    fun generateData() {
        val bunkera = Farm(name = "Bunkera", description = "Bunkera is a small family garden, packed with diverse traditional and exotic produce", site = Farm.Site(address = "55 Bistrishko Shose", city = "Sofia", country = "Bulgaria", postalCode = "1000", directions = "After a sharp turn on the road to Bistrica", hours = "Mon closed, Tue-Fri 8-18h, Sat-Sun 10-20h"))
        val zahari = Farm(name = "Zahari Stoyanovo", site = Farm.Site(city = "Zahari Stoianovo", country = "Bulgaria"))
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
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
class BootstrapData : ApplicationListener<ContextRefreshedEvent> {
    private var farmRepository: FarmRepository
    private var farmerRepository: FarmerRepository
    private var productRepository: ProductRepository
    private var categoryRepository: CategoryRepository


    constructor(farmRepository: FarmRepository, farmerRepository: FarmerRepository, productRepository: ProductRepository, categoryRepository: CategoryRepository) {
        this.farmRepository = farmRepository
        this.farmerRepository = farmerRepository
        this.productRepository = productRepository
        this.categoryRepository = categoryRepository
    }


    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        generateData()
    }

    fun generateData() {
        var bunkera: Farm = Farm(name = "Bunkera", site = Farm.Site(address = "55 Bistrishko Shose", city = "Sofia", country = "Bulgaria", postalCode = "1000", directions = "After a sharp turn on the road to Bistrica"))
        var zahari: Farm = Farm(name = "Zahari Stoyanovo", site = Farm.Site(city = "Zahari Stoianovo", country = "Bulgaria"))
        farmRepository.save(bunkera)
        farmRepository.save(zahari)

        var hristo: Farmer = Farmer(firstName = "Hristo", lastName = " Aladjov", email = "something@technologygardens.com", telephone = "08887777555", web = "technologygardens.com")
        bunkera.farmers.add(hristo)
        hristo.farms.add(bunkera)
        farmerRepository.save(hristo)

        var tsvetan: Farmer = Farmer(firstName = "Tsvetan", lastName = "Aladjov", email = "something@abv.bg ", telephone = "028620000")
        bunkera.farmers.add(tsvetan)
        tsvetan.farms.add(bunkera)
        farmerRepository.save(tsvetan)

        var mladen: Farmer = Farmer(firstName = "Mladen",lastName = "Aladjov", email = "somethinig@yahoo.com ")
        bunkera.farmers.add(mladen)
        mladen.farms.add(bunkera)
        zahari.farmers.add(mladen)
        mladen.farms.add(zahari)
        farmerRepository.save(mladen)

        var apples: Product = Product(name = "apples")
        bunkera.products.add(apples)
        apples.farms.add(bunkera)
        zahari.products.add(apples)
        apples.farms.add(zahari)
        productRepository.save(apples)

        var strawberries: Product = Product(name = "strawberries")
        bunkera.products.add(strawberries)
        strawberries.farms.add(bunkera)
        productRepository.save(strawberries)


        var fruits: Category = Category(name = "fruits")
        fruits.products.add(strawberries)
        fruits.products.add(apples)
        strawberries.categories.add(fruits)
        apples.categories.add(fruits)
        categoryRepository.save(fruits)

        var organic: Category = Category(name = "organic/bio")
        apples.categories.add(organic)
        strawberries.categories.add(organic)
        organic.products.add(strawberries)
        organic.products.add(apples)
        categoryRepository.save(organic)
        productRepository.save(strawberries)
        productRepository.save(apples)

    }
}
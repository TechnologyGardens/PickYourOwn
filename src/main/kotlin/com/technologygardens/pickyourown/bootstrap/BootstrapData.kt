package com.technologygardens.pickyourown.bootstrap

import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.model.Product
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

    constructor(farmRepository: FarmRepository, farmerRepository: FarmerRepository, productRepository: ProductRepository) {
        this.farmRepository = farmRepository
        this.farmerRepository = farmerRepository
        this.productRepository = productRepository
    }


    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        generateData()
    }

    fun generateData() {
        var bunkera: Farm = Farm(name = "Bunkera", site = Farm.Site(address = "55 Bistrishko Shose", city = "Sofia", country = "Bulgaria", postalCode = "1000", directions = "After a sharp turn on the road to Bistrica"))
        var zahari: Farm = Farm(name = "Zahari Stoyanovo", site = Farm.Site(city = "Zahari Stoianovo", country = "Bulgaria"))

        var hristo: Farmer = Farmer(name = "Hristo Aladjov", email = "something@technologygardens.com", telephone = "08887777555", web = "technologygardens.com")
        bunkera.farmers.add(hristo)
        hristo.farms.add(bunkera)

        var tsvetan: Farmer = Farmer(name = "Tsvetan Aladjov", email = "something@abv.bg ", telephone = "028620000")
        bunkera.farmers.add(tsvetan)
        tsvetan.farms.add(bunkera)

        var mladen: Farmer = Farmer(name = "Mladen Aladjov", email = "somethinig@yahoo.com ")
        bunkera.farmers.add(mladen)
        mladen.farms.add(bunkera)
        zahari.farmers.add(mladen)
        mladen.farms.add(zahari)

        var apples: Product = Product(name = "apples")
        bunkera.products.add(apples)
        apples.farms.add(bunkera)
        zahari.products.add(apples)
        apples.farms.add(zahari)

        var strawberries: Product = Product(name = "strawberries")
        bunkera.products.add(strawberries)
        strawberries.farms.add(bunkera)

        farmRepository.save(bunkera)
        farmRepository.save(zahari)
        farmerRepository.save(hristo)
        farmerRepository.save(mladen)
        farmerRepository.save(tsvetan)
        productRepository.save(apples)
        productRepository.save(strawberries)
    }
}
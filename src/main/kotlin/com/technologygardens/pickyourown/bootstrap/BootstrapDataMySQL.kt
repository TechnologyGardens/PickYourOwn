package com.technologygardens.pickyourown.bootstrap

import com.technologygardens.pickyourown.model.Category
import com.technologygardens.pickyourown.repositories.CategoryRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
@Profile("dev-mysql","prod-mysql","dev-postgres","prod-postgres")
class BootstrapDataMySQL(private var categoryRepository: CategoryRepository) : ApplicationListener<ContextRefreshedEvent> {


    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (categoryRepository.count()==0L)
          generateData()
    }

    fun generateData() {
        categoryRepository.save(Category(name = "fruits"))
        categoryRepository.save(Category(name = "organic/bio"))
        categoryRepository.save(Category(name = "dairy"))
        categoryRepository.save(Category(name = "meat"))
        categoryRepository.save(Category(name = "confections"))
        categoryRepository.save(Category(name = "vegetables"))
        categoryRepository.save(Category(name = "cereals (grain, bean and legumes)"))
    }
}
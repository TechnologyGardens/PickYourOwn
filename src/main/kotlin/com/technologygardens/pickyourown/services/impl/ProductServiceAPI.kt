package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.repositories.ProductRepository
import com.technologygardens.pickyourown.services.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceAPI(private val productRepository: ProductRepository) : ProductService {

    override fun getProducts(): Iterable<Product> = this.productRepository.findAll()


    override fun getProductById(id: String): Product {
        val productOpt: Optional<Product> = productRepository.findById(id)
        if (!productOpt.isPresent)
            throw NotFoundException("Product with Id=${id} not found!")
        return productOpt.get()
    }

    override fun save(product: Product): Product {
        logger.debug("Save product ${product.name} ($product)")
        return this.productRepository.save(product)
    }

    override fun deleteById(id: String) {
        FarmServiceAPI.logger.debug("Delete product ${id}")
        try {
            this.productRepository.deleteById(id)
        } catch (e: Exception) {
            throw NotFoundException("Product with Id=${id} not found! Can not delete non existing product")
        }
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

}
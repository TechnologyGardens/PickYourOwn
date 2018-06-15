package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.model.Product
import com.technologygardens.pickyourown.repositories.ProductRepository
import com.technologygardens.pickyourown.services.ProductService
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceAPI(private val productRepository: ProductRepository) : ProductService {

    override fun getProducts(): Iterable<Product> = this.productRepository.findAll()


    override fun getProductById(id: Long): Product {
        val productOpt: Optional<Product> = productRepository.findById(id)
        if (!productOpt.isPresent)
            throw NotFoundException("Product with Id=${id} not found!")
        return productOpt.get()
    }
}
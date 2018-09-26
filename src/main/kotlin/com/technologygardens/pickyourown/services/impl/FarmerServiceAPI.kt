package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.repositories.FarmerRepository
import com.technologygardens.pickyourown.services.FarmerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class FarmerServiceAPI(private val farmerRepository: FarmerRepository) : FarmerService {

    override fun getFarmers(): Iterable<Farmer> = this.farmerRepository.findAll()


    override fun getFarmerById(id: String): Farmer {
        val farmerOpt: Optional<Farmer> = farmerRepository.findById(id)
        if (!farmerOpt.isPresent)
            throw NotFoundException("Farmer with Id=${id} not found!")
        return farmerOpt.get()
    }

    override fun save(farmer: Farmer) : Farmer
    {
        FarmServiceAPI.logger.debug("Save new farm ${farmer.getName()} ($farmer)")
        return this.farmerRepository.save(farmer)
    }

    override fun deleteById(id: String) {
        FarmServiceAPI.logger.debug("Delete farm ${id}")
        try {
            this.farmerRepository.deleteById(id)
        } catch (e: Exception) {
            throw NotFoundException("Farmer with Id=${id} not found! Can not delete non existing farmer")
        }
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

}
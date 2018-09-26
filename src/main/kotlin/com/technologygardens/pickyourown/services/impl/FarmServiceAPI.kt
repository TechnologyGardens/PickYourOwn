package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.exceptions.NotFoundException
import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.repositories.FarmRepository
import com.technologygardens.pickyourown.services.FarmService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class FarmServiceAPI(private val farmRepository: FarmRepository) : FarmService {

    override fun getFarms(): Iterable<Farm> = this.farmRepository.findAll()


    override fun getFarmById(id: String): Farm {
        val farmOpt: Optional<Farm> = farmRepository.findById(id)
        if (!farmOpt.isPresent)
            throw NotFoundException("Farm with Id=${id} not found!")
        return farmOpt.get()
    }

    override fun save(farm: Farm): Farm {
        logger.debug("Save new farm ${farm.name} ($farm)")
        return this.farmRepository.save(farm)
    }


    override fun deleteById(id: String) {
        logger.debug("Delete farm ${id}")
        try {
            this.farmRepository.deleteById(id)
        } catch (e: Exception) {
            throw NotFoundException("Farm with Id=${id} not found! Can not delete non existing farm")
        }
    }


    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.model.Farmer
import com.technologygardens.pickyourown.repositories.FarmerRepository
import com.technologygardens.pickyourown.services.FarmerService
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class FarmerServiceAPI(private val farmerRepository: FarmerRepository) : FarmerService {

    override fun getFarmers(): Iterable<Farmer> = this.farmerRepository.findAll()


    override fun getFarmerById(id: Long): Farmer {
        val farmerOpt: Optional<Farmer> = farmerRepository.findById(id)
        if (!farmerOpt.isPresent)
            throw NotFoundException("Farmer with Id=${id} not found!")
        return farmerOpt.get()
    }

}
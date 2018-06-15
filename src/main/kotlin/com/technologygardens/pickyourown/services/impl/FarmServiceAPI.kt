package com.technologygardens.pickyourown.services.impl

import com.technologygardens.pickyourown.model.Farm
import com.technologygardens.pickyourown.repositories.FarmRepository
import com.technologygardens.pickyourown.services.FarmService
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class FarmServiceAPI(private val farmRepository: FarmRepository) : FarmService {

    override fun getFarms(): Iterable<Farm> = this.farmRepository.findAll()


    override fun getFarmById(id: Long): Farm {
        val farmOpt: Optional<Farm> = farmRepository.findById(id)
        if (!farmOpt.isPresent)
            throw NotFoundException("Farm with Id=${id} not found!")
        return farmOpt.get()
    }
}
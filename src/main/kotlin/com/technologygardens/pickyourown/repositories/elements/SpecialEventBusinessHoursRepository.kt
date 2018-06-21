package com.technologygardens.pickyourown.repositories.elements

import com.technologygardens.pickyourown.model.elements.SpecialEventBusinessHours
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SpecialEventBusinessHoursRepository : CrudRepository<SpecialEventBusinessHours,Long> {
}
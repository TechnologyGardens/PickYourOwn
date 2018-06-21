package com.technologygardens.pickyourown.repositories.elements

import com.technologygardens.pickyourown.model.elements.RegularBusinessHours
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RegularBusinessHoursReposiory : CrudRepository<RegularBusinessHours,Long> {
}
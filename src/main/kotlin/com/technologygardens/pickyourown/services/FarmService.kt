package com.technologygardens.pickyourown.services

import com.technologygardens.pickyourown.model.Farm
import javassist.NotFoundException
import java.util.*

interface FarmService {
    fun getFarms(): Iterable<Farm>
    fun getFarmById(id: Long): Farm
}
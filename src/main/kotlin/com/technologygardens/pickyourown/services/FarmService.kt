package com.technologygardens.pickyourown.services

import com.technologygardens.pickyourown.model.Farm

interface FarmService {
    fun getFarms(): Iterable<Farm>
    fun getFarmById(id: Long): Farm
    fun deleteById(id: Long)
    fun save(farm: Farm) : Farm
}
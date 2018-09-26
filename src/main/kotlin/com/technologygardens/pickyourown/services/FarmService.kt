package com.technologygardens.pickyourown.services

import com.technologygardens.pickyourown.model.Farm

interface FarmService {
    fun getFarms(): Iterable<Farm>
    fun getFarmById(id: String): Farm
    fun deleteById(id: String)
    fun save(farm: Farm) : Farm
}
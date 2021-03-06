package com.technologygardens.pickyourown.services

import com.technologygardens.pickyourown.model.Farmer

interface FarmerService {
    fun getFarmers(): Iterable<Farmer>
    fun getFarmerById(id: Long): Farmer
    fun deleteById(id: Long)
    fun save(farmer: Farmer) : Farmer
}
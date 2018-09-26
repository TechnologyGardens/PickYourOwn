package com.technologygardens.pickyourown.services

import com.technologygardens.pickyourown.model.Farmer

interface FarmerService {
    fun getFarmers(): Iterable<Farmer>
    fun getFarmerById(id: String): Farmer
    fun deleteById(id: String)
    fun save(farmer: Farmer) : Farmer
}
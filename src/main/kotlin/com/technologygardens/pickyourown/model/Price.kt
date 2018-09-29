package com.technologygardens.pickyourown.model

import java.math.BigDecimal
import java.util.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Price(
        @Id
        val id: String = UUID.randomUUID().toString(),
        @DBRef
        val farm: Farm = Farm(),
        @DBRef
        val product: Product = Product(),
        var value: BigDecimal = BigDecimal.ZERO,
        val unit: String = "$/kg",
        var minQuantity: Int = 0,
        var maxQuantity: Int = Int.MAX_VALUE) {
        fun getFormattedPrice() :String{
               return "(${value.toString().format("0.00##")} ${unit})"
        }
}
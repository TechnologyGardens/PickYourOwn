package com.technologygardens.pickyourown.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
class Price(
        @Id
        @GeneratedValue(generator = "db-uuid")
        @GenericGenerator(name="db-uuid", strategy = "uuid")
        val id: String = "",
        @ManyToOne
        @JoinColumn(name = "farm_id")
        val farm: Farm = Farm(),
        @ManyToOne
        @JoinColumn(name = "product_id")
        val product: Product = Product(),
        var value: BigDecimal = BigDecimal.ZERO,
        val unit: String = "$/kg",
        @OrderBy("minQuantity")
        var minQuantity: Int = 0,
        var maxQuantity: Int = Int.MAX_VALUE) {
        fun getFormattedPrice() :String{
               return "(${value.toString().format("0.00##")} ${unit})"
        }
}
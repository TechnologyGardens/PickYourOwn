package com.technologygardens.pickyourown.model.elements

import com.technologygardens.pickyourown.model.Farm
import org.hibernate.validator.constraints.URL
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
data class Site(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        @field:Size(min=0, max = 255)
        var address: String = "",
        @field:Size(min=0, max = 255)
        var city: String = "",
        @field:Size(min=0, max = 255)
        var stateProvince: String = "",
        @field:Size(min=0, max = 255)
        var country: String = "",
        @field:Size(min=0, max = 255)
        var postalCode: String = "",
        @field:NotBlank
        var directions: String = "Please provide directions to your farm site",
        @OneToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "regular_business_hours_id")
        var regularBusinessHours: MutableSet<RegularBusinessHours> = mutableSetOf<RegularBusinessHours>(),
        @OneToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "special_event_business_hours_id")
        var specialEventBusinessHours: MutableSet<SpecialEventBusinessHours> = mutableSetOf<SpecialEventBusinessHours>(),
        var seasonOpens: String = "",
        var seasonCloses: String = "",
        @field:URL
        var social: String = "",
        @field:URL
        var web: String = ""

) {
    init {
        if (seasonOpens.equals("") && (regularBusinessHours.size > 0)) {
            val opens = regularBusinessHours.first().opensAt
            val closes = regularBusinessHours.first().closesAt
            this.seasonOpens = "${opens.dayOfYear} ${opens.month.name}"
            this.seasonCloses = "${closes.dayOfYear} ${closes.month.name}"
        }
    }

    @OneToOne
    var farm: Farm? = null
        set (value) {
            field = value
            if (field?.site!= this)
                field?.site = this
        }


    fun getBusinessHours():Array<String>{
        val hours = Array<String>(this.regularBusinessHours.size+this.specialEventBusinessHours.size){""}
        var index = 0
        for (regularHours in this.regularBusinessHours)
          hours[index++] =   "${regularHours.getDaysOfTheWeekDescription()}: ${regularHours.getHoursDescription()}"
        for (specialEvent in this.specialEventBusinessHours)
            hours[index++] =   specialEvent.getDescription()
        return hours
    }
}

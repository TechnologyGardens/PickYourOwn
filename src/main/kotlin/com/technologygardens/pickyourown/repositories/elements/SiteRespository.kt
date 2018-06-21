package com.technologygardens.pickyourown.repositories.elements

import com.technologygardens.pickyourown.model.elements.Site
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SiteRespository : CrudRepository<Site,Long>{
}

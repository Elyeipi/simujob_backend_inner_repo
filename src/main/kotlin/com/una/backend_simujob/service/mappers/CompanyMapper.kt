package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.Company
import com.una.backend_simujob.service.dtos.CompanyOutput
import com.una.backend_simujob.service.dtos.CompanyProfile
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = [LocationMapper::class]
)
interface CompanyMapper {
    fun companyEntityToCompanyOutput(entity: Company): CompanyOutput

    @Mapping(source = "company.name", target = "name")
    @Mapping(source = "company.legalId", target = "legalId")
    @Mapping(source = "company.email", target = "email")
    @Mapping(source = "contact.phone", target = "contactPhone")
    @Mapping(source = "contact.website", target = "webSite")
    @Mapping(source = "address.country", target = "location.country")
    @Mapping(source = "address.city", target = "location.city")
    @Mapping(source = "address.address", target = "location.address")
    @Mapping(source = "business.sector", target = "industry")
    @Mapping(source = "business.description", target = "description")
    @Mapping(source = "social.linkedin", target = "linkedin")
    @Mapping(source = "social.facebook", target = "facebook")
    @Mapping(source = "social.twitter", target = "twitter")
    @Mapping(source = "legalRep.firstName", target = "legalRepName")
    @Mapping(source = "legalRep.lastName", target = "legalRepLastNames")
    @Mapping(source = "legalRep.email", target = "legalRepEmail")
    fun companyRegisterDtoToCompanyEntity(dto: CompanyProfile): Company;

    @Mapping(source = "name", target = "company.name")
    @Mapping(source = "legalId", target = "company.legalId")
    @Mapping(source = "email", target = "company.email")
    @Mapping(target = "company.password", constant = "THIS_IS_NOT_THE_REAL_PASSWORD")

    @Mapping(source = "contactPhone", target = "contact.phone")
    @Mapping(source = "webSite", target = "contact.website")

    @Mapping(source = "location.country", target = "address.country")
    @Mapping(source = "location.city", target = "address.city")
    @Mapping(source = "location.address", target = "address.address")

    @Mapping(source = "industry", target = "business.sector")
    @Mapping(source = "description", target = "business.description")

    @Mapping(source = "linkedin", target = "social.linkedin")
    @Mapping(source = "facebook", target = "social.facebook")
    @Mapping(source = "twitter", target = "social.twitter")

    @Mapping(source = "legalRepName", target = "legalRep.firstName")
    @Mapping(source = "legalRepLastNames", target = "legalRep.lastName")
    @Mapping(source = "legalRepEmail", target = "legalRep.email")
    fun companyEntityToCompanyProfile(entity: Company): CompanyProfile
}

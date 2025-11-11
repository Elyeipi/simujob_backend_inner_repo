package com.una.backend_simujob.webservice

import com.una.backend_simujob.service.dtos.*
import com.una.backend_simujob.service.interfaces.CompanyService
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.companies}")
class CompanyController(private val companyService: CompanyService) {
    @PostMapping(
        "login",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun login(@RequestBody credentialInput: CredentialInput): ApiResponse<CompanyAuthResponse> {
        return companyService.login(credentialInput)
    }


    @PostMapping(
        "signup",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun addCompany(@RequestBody companyInput: CompanyProfile): CompanyOutput {
        return companyService.addCompany(companyInput)
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): CompanyOutput? {
        return companyService.finById(id)
    }

    @PatchMapping(
        "{id}/update",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateCompany(
        @PathVariable companyId: Long,
        @RequestBody companyInput: CompanyUpdateInput
    ): ApiResponse<CompanyProfile> {
        return companyService.updateCompany(companyId, companyInput);
    }

    @PutMapping(
        "me/profile/update",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateMyCompanyProfile(
        @RequestBody companyInput: CompanyUpdateDto,
        authentication: Authentication
    ): ApiResponse<CompanyProfile> {
        // authentication.name is the JWT subject (your company.email)
        val email = authentication.name
        return companyService.updateCompanyProfile(email, companyInput)
    }

    @GetMapping(
        "health"
    )
    fun healthCheck(): String {
        return "OK"
    }

    @GetMapping("me")
    fun getMyCompany(authentication: Authentication): String {
        // authentication.name is the JWT subject (your company.email)
        val email = authentication.name
        // then look up the company by email and return its profile
        return email
    }

    @GetMapping("me/profile")
    fun getMyCompanyProfile(authentication: Authentication): ApiResponse<CompanyProfile> {
        // authentication.name is the JWT subject (your company.email)
        val email = authentication.name
        // then look up the company by email and return its profile
        return companyService.getMyCompanyProfile(email)
    }

    @GetMapping("me/job-offers")
    fun getMyJobOffers(authentication: Authentication): ApiResponse<List<JobSummary>> {
        // authentication.name is the JWT subject (your company.email)
        val email = authentication.name
        // then look up the company by email and return its job offers
        return companyService.getMyJobOffers(email)
    }

    @GetMapping("me/job-offers/{jobOfferId}")
    fun getMyJobOffer(
        @PathVariable jobOfferId: Long,
        authentication: Authentication
    ): ApiResponse<JobDetails> {
        // authentication.name is the JWT subject (your company.email)
        val email = authentication.name
        return companyService.getMyJobOffer(email, jobOfferId)
    }

    @PostMapping("me/post-job-offer")
    fun postJobOffer(
        @RequestBody jobOfferInput: JobCreateDto,
        authentication: Authentication
    ): ApiResponse<JobCreateResponseDto> {
        // authentication.name is the JWT subject (your company.email)
        val email = authentication.name
        return companyService.postJobOffer(email, jobOfferInput)
    }
}

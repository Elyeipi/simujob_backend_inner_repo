package com.una.backend_simujob.service.interfaces

import com.una.backend_simujob.service.dtos.*


interface CompanyService {

    fun addCompany(company: CompanyProfile): CompanyOutput //Here change String to Company

    fun updateCompany(companyId: Long, input: CompanyUpdateInput): ApiResponse<CompanyProfile>

    fun finById(companyId: Long): CompanyOutput? //Here change String to Company

    fun deleteAcount(id: Long): CompanyOutput?

    fun login(credentialInput: CredentialInput): ApiResponse<CompanyAuthResponse>

    fun getMyJobOffers(email: String): ApiResponse<List<JobSummary>>

    fun postJobOffer(email: String, jobOfferInput: com.una.backend_simujob.service.dtos.JobCreateDto): com.una.backend_simujob.service.dtos.ApiResponse<com.una.backend_simujob.service.dtos.JobCreateResponseDto>

    fun getMyJobOffer(email: String, jobOfferId: Long): ApiResponse<JobDetails>

    fun getMyCompanyProfile(email: String): ApiResponse<CompanyProfile>

    fun updateCompanyProfile(email: String, updateDto: CompanyUpdateDto): ApiResponse<CompanyProfile>
}

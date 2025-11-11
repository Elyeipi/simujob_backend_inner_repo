package com.una.backend_simujob.service.implementation

import com.una.backend_simujob.data.repository.CompanyRepository
import com.una.backend_simujob.data.repository.JobRepository
import com.una.backend_simujob.security.SecurityConstants
import com.una.backend_simujob.security.shared.key
import com.una.backend_simujob.service.dtos.*
import com.una.backend_simujob.service.interfaces.CompanyService
import com.una.backend_simujob.service.mappers.CompanyMapper
import com.una.backend_simujob.service.mappers.JobMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class AbstractCompanyService(
    @Autowired
    private val companyRepository: CompanyRepository,
    @Autowired
    private val companyMapper: CompanyMapper,
    @Autowired
    private val jobRepository: JobRepository,
    @Autowired
    private val jobMapper: JobMapper,
    @Autowired
    private val hashService: HashService,
    @Autowired
    private val authManager: AuthenticationManager
) : CompanyService {

    override fun login(credentials: CredentialInput): ApiResponse<CompanyAuthResponse> {
        // log the login attempt
        println("Login attempt for email: ${credentials.email}")


        // 1) authenticate
        val authToken = UsernamePasswordAuthenticationToken(
            credentials.email, credentials.password
        )
        val auth = authManager.authenticate(authToken)

        // 2) generate JWT
        val rawToken = Jwts.builder()
            .signWith(key(), SignatureAlgorithm.HS512)
            .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
            .setIssuer(SecurityConstants.TOKEN_ISSUER)
            .setAudience(SecurityConstants.TOKEN_AUDIENCE)
            .setSubject(auth.name)
            .setExpiration(Date(System.currentTimeMillis() + SecurityConstants.TOKEN_LIFETIME))
            .compact()

        // 3) lookup company
        val company = companyRepository.findByEmail(auth.name)
            ?: throw UsernameNotFoundException("Company not found: ${auth.name}")

        val summary = CompanySummary(
            id = company.id!!,
            name = company.name.orEmpty(),
            email = company.email.orEmpty()
        )

        // 4) build response DTO
        val authResponse = CompanyAuthResponse(
            token = rawToken,
            expiresIn = SecurityConstants.TOKEN_LIFETIME,
            company = summary
        )

        // 5) wrap in ApiResponse
        return ApiResponse(
            success = true,
            message = "Login successful",
            data = authResponse
        )
    }

    override fun addCompany(company: CompanyProfile): CompanyOutput {
        val companyEntity = companyMapper.companyRegisterDtoToCompanyEntity(company)
        val location = companyEntity.location
        location?.company = companyEntity

        // Store password hashed
        val hashedPassword = hashService.hashPassword(company.company.password)
        companyEntity.password = hashedPassword

        val response = companyRepository.save(companyEntity)
        return companyMapper.companyEntityToCompanyOutput(response)
    }

    override fun updateCompany(companyId: Long, input: CompanyUpdateInput): ApiResponse<CompanyProfile> {
        val optional = companyRepository.findById(companyId)
            ?: return ApiResponse(false, "Company not found", null)

        val company = optional.get()


        input.juridicalId?.let { company.legalId = it }
        input.companyEmail?.let { company.email = it }
        input.password?.let { company.password = hashService.hashPassword(it) }
        input.contactPhone?.let { company.contactPhone = it }
        input.businessWebsite?.let { company.webSite = it }
        input.industrySector?.let { company.industry = it }
        input.shortDescription?.let { company.description = it }

        input.socialMedia?.let {
            company.linkedin = it.linkedin
            company.facebook = it.facebook
            company.twitter = it.twitter
        }

        input.legalRepresentative?.let {
            company.legalRepName = it.fullName
            company.legalRepLastNames = it.lastNames
            company.legalRepEmail = it.email
        }

        val updated = companyRepository.save(company)

        return ApiResponse(
            success = true,
            message = "Company updated successfully",
            data = companyMapper.companyEntityToCompanyProfile(updated),
        )
    }

    @Throws(NoSuchElementException::class)
    override fun finById(companyId: Long): CompanyOutput? {
        val company = companyRepository.findById(companyId).orElseThrow { NoSuchElementException("Company not found!") }
        return companyMapper.companyEntityToCompanyOutput(company)
    }

    override fun deleteAcount(id: Long): CompanyOutput? {
        val companyEntity = companyRepository.findById(id).orElseThrow { NoSuchElementException("Company not found!") }

        return companyMapper.companyEntityToCompanyOutput(companyEntity)
    }

    override fun getMyJobOffers(email: String): ApiResponse<List<JobSummary>> {
        val company = companyRepository.findByEmail(email)
            ?: return ApiResponse(false, "Company not found", null)

        val jobOffers = jobRepository.findByCompanyId(company.id!!)
            .map { job -> jobMapper.jobEntityToJobSummary(job) }

        return ApiResponse(
            success = true,
            message = "Job offers retrieved successfully",
            data = jobOffers
        )
    }

    override fun postJobOffer(email: String, jobOfferInput: JobCreateDto): ApiResponse<JobCreateResponseDto> {
        val company = companyRepository.findByEmail(email)
            ?: return ApiResponse(false, "Company not found", null)

        val jobEntity = jobMapper.jobCreateDtoToJobEntity(jobOfferInput)
        jobEntity.company = company
        jobEntity.postDate = Date() // post date is now

        val savedJob = jobRepository.save(jobEntity)

        val responseDto = jobMapper.jobEntityToJobCreateResponseDto(savedJob)

        return ApiResponse(
            success = true,
            message = "Job offer posted successfully",
            data = responseDto
        )
    }

    override fun getMyJobOffer(email: String, jobOfferId: Long): ApiResponse<JobDetails> {
        val company = companyRepository.findByEmail(email)
            ?: return ApiResponse(false, "Company not found", null)

        val jobOffer = jobRepository.findById(jobOfferId)
            .orElseThrow { NoSuchElementException("Job offer not found") }

        if (jobOffer.company?.id != company.id) {
            return ApiResponse(false, "You do not have permission to access this job offer", null)
        }

        val jobDetails = jobMapper.jobEntityToJobDetails(jobOffer)

        return ApiResponse(
            success = true,
            message = "Job offer retrieved successfully",
            data = jobDetails
        )
    }

    override fun getMyCompanyProfile(email: String): ApiResponse<CompanyProfile> {
        val company = companyRepository.findByEmail(email)
            ?: return ApiResponse(false, "Company not found", null)

        val profile = companyMapper.companyEntityToCompanyProfile(company)

        return ApiResponse(
            success = true,
            message = "Company profile retrieved successfully",
            data = profile
        )
    }

    override fun updateCompanyProfile(email: String, updateDto: CompanyUpdateDto): ApiResponse<CompanyProfile> {
        val company = companyRepository.findByEmail(email)
            ?: return ApiResponse(false, "Company not found", null)

        // update fields based on input
        updateDto.company?.let {
            if (it.name != null) company.name = it.name
            if (it.legalId != null) company.legalId = it.legalId
        }

        updateDto.contact?.let {
            if (it.phone != null) company.contactPhone = it.phone
            if (it.website != null) company.webSite = it.website
        }

        updateDto.business?.let {
            if (it.sector != null) company.industry = it.sector
            if (it.description != null) company.description = it.description
        }

        updateDto.legalRep?.let {
            if (it.firstName != null) company.legalRepName = it.firstName
            if (it.lastName != null) company.legalRepLastNames = it.lastName
            if (it.email != null) company.legalRepEmail = it.email
        }

        updateDto.social?.let {
            if (it.linkedin != null) company.linkedin = it.linkedin
            if (it.facebook != null) company.facebook = it.facebook
            if (it.twitter != null) company.twitter = it.twitter
        }

        updateDto.address?.let {
            val loc = company.location
            if (loc != null) {
                if (it.country != null) loc.country = it.country
                if (it.city != null) loc.city = it.city
                if (it.address != null) loc.address = it.address
            }
        }

        val updatedCompany = companyRepository.save(company)

        return ApiResponse(
            success = true,
            message = "Company profile updated successfully",
            data = companyMapper.companyEntityToCompanyProfile(updatedCompany)
        )
    }


}

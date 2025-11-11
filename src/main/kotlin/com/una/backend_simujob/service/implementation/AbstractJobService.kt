package com.una.backend_simujob.service.implementation

import com.una.backend_simujob.data.entity.JobApplication
import com.una.backend_simujob.data.entity.JobApplicationKey
import com.una.backend_simujob.data.repository.CompanyRepository
import com.una.backend_simujob.data.repository.JobApplicationRepository
import com.una.backend_simujob.data.repository.JobRepository
import com.una.backend_simujob.data.repository.UserRepository
import com.una.backend_simujob.service.dtos.*
import com.una.backend_simujob.service.interfaces.JobService
import com.una.backend_simujob.service.mappers.CompanyMapper
import com.una.backend_simujob.service.mappers.JobMapper
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.Date
import kotlin.jvm.Throws

@Service
@Transactional
class AbstractJobService(
    @Autowired
    private val jobRepository: JobRepository,
    @Autowired
    private val companyRepository: CompanyRepository,
    @Autowired
    private val jobMapper: JobMapper,
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val jobApplicationRepository: JobApplicationRepository,
    @Autowired
    private val companyMapper: CompanyMapper
) : JobService{

    @Throws(NoSuchElementException::class)
    override fun searchJobByParam(params: String): List<JobOutput>?{
        val jobs = jobRepository.findByTitleParam(params)
        if(jobs.isEmpty()){
            throw NoSuchElementException("No jobs find with param: $params")
        }
        return jobMapper.jobEntityListtoJobOutputList(jobs)
    }

    @Throws(NoSuchElementException::class)
    override fun getLastJobApplicationsByUser(userId: Long) : List<JobOutput>?{
        val applicationsByUser = jobApplicationRepository.findJobByUserId(userId)
        val jobs = applicationsByUser.map { job -> job.job }
        if(jobs.isEmpty()){
            throw NoSuchElementException("User does not has jobs applications")
        }
        return jobMapper.jobEntityListtoJobOutputList(jobs.filterNotNull())
    }

    override fun getJobsPostedByCompany(companyId: Long): List<JobOutput>?{
        val jobs = jobRepository.findByCompanyId(companyId)
        return jobMapper.jobEntityListtoJobOutputList(jobs)
    }

    @Throws(NoSuchElementException::class)
    override fun getCompanyByJobId(jobId: Long): CompanyOutPutWithJobs?{
        val jobEntity = jobRepository.findById(jobId).orElseThrow { NoSuchElementException("Job with id $jobId not found") }
        val jobCompany = jobEntity.company
        val jobsByCompany = jobRepository.findSomeJobsByCompanyId(jobCompany?.id ?: throw NoSuchElementException("Company with id $jobCompany not found"), PageRequest.of(0, 4))
        val companyWithJobs = CompanyOutPutWithJobs(company = companyMapper.companyEntityToCompanyOutput(jobCompany), jobs = jobMapper.jobEntityListtoJobOutputList(jobsByCompany))
        return companyWithJobs
    }

    @Throws(NoSuchElementException::class)
    override fun addJob(job: JobInput) : JobOutput {
        val companyEntity = companyRepository.findById(job.companyId).orElseThrow{ NoSuchElementException("Company not found with the provide id") }
        val jobEntity = jobMapper.jobInputToJobEntity(job)
        jobEntity.company = companyEntity
        return jobMapper.jobEntityToJobOutput(jobRepository.save(jobEntity))
    }

    @Throws(NoSuchElementException::class)
    override fun applyJob(application: ApplicationDTO) : JobOutput{
        val userEntity = userRepository.findById(application.userId).orElseThrow { NoSuchElementException("User not found with the provide id") }
        val jobEntity = jobRepository.findById(application.jobId).orElseThrow { NoSuchElementException("Job not found with the provide id") }
        val applicationId = JobApplicationKey(userId = userEntity.id!!, jobId = jobEntity.id!!)
        val existsApplication = jobApplicationRepository.findById(applicationId)
        if(existsApplication.isPresent){
            throw Exception("Already exists application by user with id ${application.userId} for job with id ${application.jobId}")
        }
        val jobApplicationEntity = JobApplication(id = applicationId, user = userEntity, job = jobEntity, applicationDate = Date())
        val applicationDB = jobApplicationRepository.save(jobApplicationEntity)
        return jobMapper.jobEntityToJobOutput(applicationDB.job!!)
    }
}
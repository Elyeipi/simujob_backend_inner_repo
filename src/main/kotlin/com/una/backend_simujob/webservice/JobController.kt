package com.una.backend_simujob.webservice

import com.una.backend_simujob.service.dtos.*
import com.una.backend_simujob.service.interfaces.JobService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${url.jobs}")
class JobController(private val jobService: JobService) {
    @GetMapping("params/{params}")
    fun searchJobByParam(@PathVariable params : String) : List<JobOutput>?{
        return jobService.searchJobByParam(params)
    }

    @GetMapping("user/{id}")
    fun getLasJobApplicationsByUser(@PathVariable id : Long) : List<JobOutput>?{
        return jobService.getLastJobApplicationsByUser(id)
    }

    @GetMapping("find/{id}")
    fun getCompanyBuJobId(@PathVariable id : Long) : CompanyOutPutWithJobs?{
        return jobService.getCompanyByJobId(id)
    }

    @GetMapping("company/{id}")
    fun getJobsPostedByCompany(@PathVariable id : Long) : List<JobOutput>?{
        return jobService.getJobsPostedByCompany(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addJob(@RequestBody jobInput: JobInput) : JobOutput{
        return jobService.addJob(jobInput)
    }

    @PostMapping("/apply", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun applyJob(@RequestBody applicationDTO: ApplicationDTO) : JobOutput{
        return jobService.applyJob(applicationDTO)
    }
}
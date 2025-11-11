//package com.una.backend_simujob.dataLayerTest
//
//import com.una.backend_simujob.data.entity.Company
//import com.una.backend_simujob.data.entity.Job
//import com.una.backend_simujob.data.entity.User
//import com.una.backend_simujob.data.repository.CompanyRepository
//import com.una.backend_simujob.data.repository.JobRepository
//import com.una.backend_simujob.data.repository.UserRepository
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.transaction.annotation.Transactional
//import java.sql.Date
//import java.time.LocalDateTime
//import kotlin.test.assertEquals
//import kotlin.test.assertFalse
//import kotlin.test.assertNotNull
//
//@SpringBootTest
//@Transactional
//class JobRepositoryTest(
//    @Autowired
//    val userRepository: UserRepository,
//
//    @Autowired
//    val companyRepository: CompanyRepository,
//
//    @Autowired
//    val jobRepository: JobRepository,
//) {
//
//    @Test
//    fun insertJob() {
//        val user = User(
//            firstName = "JobUser",
//            lastName = "Tester",
//            email = "jobtester-\${java.util.UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "JobPass123",
//            tokenExpired = false,
//            aboutUser = "Job test user",
//            curriculum = null,
//            suscription = 1,
//            createDate = Date(125, 4, 20)
//        )
//        val savedUser = userRepository.save(user)
//
//        val company = Company(
//            legalId = "7777777777",
//            name = "JobCorp",
//            email = "contact@jobcorp.com",
//            webSite = "https://jobcorp.com",
//            industry = "Recruitment",
//            description = "Job test company",
//            user = savedUser
//        )
//        val savedCompany = companyRepository.save(company)
//
//        val job = Job(
//            title = "Software Engineer",
//            shift = "Day",
//            workMode = "Remote",
//            description = "Develop and maintain applications",
//            jobUrl = "https://jobcorp.com/jobs/1",
//            postDate = java.util.Date(),
//            company = savedCompany
//        )
//        val savedJob = jobRepository.save(job)
//
//        assertNotNull(savedJob.id)
//        assertEquals("Software Engineer", savedJob.title)
//        assertEquals(savedCompany.id, savedJob.company?.id)
//    }
//
//    @Test
//    fun readJobById() {
//        insertJob()
//        val id = jobRepository.findAll().first().id!!
//        val found = jobRepository.findById(id).get()
//        assertEquals("Day", found.shift)
//    }
//
//    @Test
//    fun updateJob() {
//        insertJob()
//        val j = jobRepository.findAll().first()
//        j.title = "Senior Engineer"
//        j.shift = "Night"
//        val updated = jobRepository.save(j)
//
//        assertEquals("Senior Engineer", updated.title)
//        assertEquals("Night", updated.shift)
//    }
//
//    @Test
//    fun deleteJobById() {
//        insertJob()
//        val id = jobRepository.findAll().first().id!!
//        jobRepository.deleteById(id)
//        assertFalse(jobRepository.findById(id).isPresent)
//    }
//
//    @Test
//    fun associateJobWithCompany() {
//        insertJob()
//        val j = jobRepository.findAll().first()
//        val c = companyRepository.findById(j.company!!.id!!).get()
//        assertNotNull(c)
//        assertEquals(c.id, j.company!!.id)
//    }
//}

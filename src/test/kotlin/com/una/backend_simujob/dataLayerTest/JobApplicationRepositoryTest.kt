package com.una.backend_simujob.dataLayerTest

//@SpringBootTest
//@Transactional
//class JobApplicationRepositoryTest(
//    @Autowired
//    val userRepository: UserRepository,
//    @Autowired
//    val companyRepository: CompanyRepository,
//    @Autowired
//    val jobRepository: JobRepository,
//    @Autowired
//    val applicationRepository: JobApplicationRepository,
//) {
//
//    private fun setupEntities(): Pair<User, Job> {
//        val user = User(
//            firstName = "AppUser",
//            lastName = "Tester",
//            email = "apptester-\${java.util.UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "AppPass123",
//            tokenExpired = false,
//            aboutUser = "Application test",
//            curriculum = null,
//            suscription = 1,
//            createDate = Date(125, 4, 20)
//        )
//        val savedUser = userRepository.save(user)
//
//        val company = Company(
//            legalId = "9999999999",
//            name = "AppCorp",
//            email = "contact@appcorp.com",
//            webSite = "https://appcorp.com",
//            industry = "Tech",
//            description = "Application test company",
//            user = savedUser
//        )
//        val savedCompany = companyRepository.save(company)
//
//        val job = Job(
//            title = "QA Engineer",
//            shift = "Flexible",
//            workMode = "Hybrid",
//            description = "Test applications",
//            jobUrl = "https://appcorp.com/jobs/qa",
//            postDate = java.util.Date(),
//            company = savedCompany
//        )
//        val savedJob = jobRepository.save(job)
//
//        return Pair(savedUser, savedJob)
//    }
//
//    @Test
//    fun insertApplication() {
//        val (user, job) = setupEntities()
//        val application = JobApplication(
//            id = JobApplicationKey(userId = user.id!!, jobId = job.id!!),
//            user = user,
//            job = job,
//            applicationDate = LocalDateTime.now()
//        )
//        val savedApp = applicationRepository.save(application)
//
//        assertNotNull(savedApp.id)
//        assertEquals(user.id, savedApp.id.userId)
//        assertEquals(job.id, savedApp.id.jobId)
//    }
//
//    @Test
//    fun readApplicationById() {
//        insertApplication()
//        val saved = applicationRepository.findAll().first()
//        val found = applicationRepository.findAll().first()
//        assertEquals(saved.id, found.id)
//    }
//
//    @Test
//    fun updateApplicationDate() {
//        insertApplication()
//        val existing = applicationRepository.findAll().first()
//        val newDate = LocalDateTime.now().plusDays(1)
//        existing.applicationDate = newDate
//        val updated = applicationRepository.save(existing)
//        assertEquals(newDate, updated.applicationDate)
//    }
//
//    @Test
//    fun deleteApplication() {
//        insertApplication()
//        val listBefore = applicationRepository.findAll()
//        assertFalse(listBefore.isEmpty())
//
//        applicationRepository.delete(listBefore.first())
//        val listAfter = applicationRepository.findAll()
//        assertTrue(listAfter.isEmpty())
//    }
//
//    @Test
//    fun associateApplicationWithEntities() {
//        insertApplication()
//        val app = applicationRepository.findAll().first()
//        assertNotNull(app.user)
//        assertNotNull(app.job)
//        assertEquals(app.id.userId, app.user!!.id)
//        assertEquals(app.id.jobId, app.job!!.id)
//    }
//}
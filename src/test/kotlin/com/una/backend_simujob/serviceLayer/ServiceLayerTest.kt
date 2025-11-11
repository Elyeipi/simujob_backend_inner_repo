//package com.una.backend_simujob.serviceLayer
//
//import com.una.backend_simujob.service.dtos.*
//import com.una.backend_simujob.service.interfaces.*
//import jakarta.transaction.Transactional
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import java.util.*
//import kotlin.test.assertEquals
//
//@SpringBootTest
//class ServiceLayerTest (
//    @Autowired
//    private val userService: UserService,
//
//    @Autowired
//    private val paymentService: PaymentService,
//
//    @Autowired
//    private val interviewService: InterviewService,
//
//    @Autowired
//    private val achievementService: AchievementService,
//
//    @Autowired
//    private val companyService: CompanyService,
//
//    @Autowired
//    private val authenticationService: AuthenticationService,
//
//    @Autowired
//    private val jobService: JobService,
//){
//
//    @Test
//    fun insertUserByService(){
//        val user = UserInput(
//            firstName = "User test name",
//            lastName = "User test lastnames",
//            email = "apptester-${java.util.UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "MyPassword123#$",
//            tokenExpired = false,
//            aboutUser = "User about test...",
//            suscription = 2,
//        )
//
//        val response = userService.addUser(user);
//
//        System.out.println(response.id)
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun insertUserWithExtras(){
//        val user = UserInput(
//            firstName = "User test name",
//            lastName = "User test lastnames",
//            email = "apptester-${java.util.UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "MyPassword123#$",
//            tokenExpired = false,
//            aboutUser = "User about test...",
//            suscription = 2,
//            education = mutableListOf(),
//            socialNetwork = mutableListOf(),
//        )
//
//        val education = EducationDTO(
//            institutionName = "sdfdsfs",
//            degree = "bachelor",
//        )
//
//        val network = SocialNetworkDTO(
//            name = "Hola",
//            link = "https://www.hola.com",
//        )
//
//        user.education?.add(education)
//
//        user.socialNetwork?.add(network)
//
//        val response = userService.addUser(user);
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun insertUserOnlyWithEducation(){
//        val user = UserInput(
//            firstName = "User test name",
//            lastName = "User test lastnames",
//            email = "apptester-${java.util.UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "MyPassword123#$",
//            tokenExpired = false,
//            aboutUser = "User about test...",
//            suscription = 2,
//            education = mutableListOf(),
//        )
//
//        val education = EducationDTO(
//            institutionName = "Cenfotec",
//            degree = "bachelor",
//        )
//
//        user.education?.add(education)
//
//        val response = userService.addUser(user);
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun insertUserOnlyWithSocialNetwork(){
//        val user = UserInput(
//            firstName = "User test name",
//            lastName = "User test lastnames",
//            email = "apptester-${java.util.UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "MyPassword123#$",
//            tokenExpired = false,
//            aboutUser = "User about test...",
//            suscription = 2,
//            socialNetwork = mutableListOf(),
//        )
//
//        val network = SocialNetworkDTO(
//            name = "Hola",
//            link = "https://www.hola.com",
//        )
//
//        user.socialNetwork?.add(network)
//
//        val response = userService.addUser(user);
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun updateUserData(){
//        val user = UserInput(
//            id = 25,
//            firstName = "Juan",
//            lastName = "Araya Sala",
//            email = "apptester-${java.util.UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "OtraPassword123#$",
//            tokenExpired = false,
//            aboutUser = "MiDescripcion",
//            suscription = 2,
//            education = mutableListOf(),
//            socialNetwork = mutableListOf(),
//        )
//
//        val education = EducationDTO(
//            institutionName = "UNA CAMPUS BENJAMIN NUÑEZ",
//            degree = "Master",
//        )
//
//        val network = SocialNetworkDTO(
//            name = "Facebook",
//            link = "https://www.facebook.com",
//        )
//
//        user.education?.add(education)
//
//        user.socialNetwork?.add(network)
//
//        val response = userService.updateUser(user);
//
//        assert(response.id != null)
//
//    }
//
//    @Test
//    fun updateUser(){
//        val user = UserInput(
//            id = 4,
//            firstName = "User test name",
//            lastName = "User test lastnames",
//            email = "apptester-2fd4c488-33e0-487d-99cb-75b1548c8045@example.com",
//            enabled = true,
//            password = "MyOtherNotPassword123#$",
//            tokenExpired = false,
//            aboutUser = "User about test...",
//            suscription = 2,
//            education = mutableListOf(),
//            socialNetwork = mutableListOf(),
//        )
//
//        val education = EducationDTO(
//            institutionName = "sdfdsfs",
//            degree = "bachelor",
//        )
//
//        val network = SocialNetworkDTO(
//            name = "Hola",
//            link = "https://www.hola.com",
//        )
//
//        user.education?.add(education)
//
//        user.socialNetwork?.add(network)
//
//        val response = userService.updateUser(user);
//
//        assert(response.id != null)
//    }
//
//    @Transactional
//    @Test
//    fun readUserById(){
//        val user = userService.findById(25)
//
//        System.out.println(user.toString())
//
//        assertEquals(user?.id, 25)
//    }
//
//    @Test
//    fun addPaymentCard(){
//        val paymentCard = PaymentCardDTO(
//            cardNumber = "5555 5555 5555 5555",
//            expirationDate = Date(),
//            cvv = "123",
//            cardHolderName = "Juan carlos",
//            userId = 25
//        )
//
//        val response = paymentService.addPaymentCard(paymentCard)
//
//        System.out.println(response.toString())
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun getPaymentsByUserId(){
//        val userId = 25L;
//        val response = paymentService.getPaymentCards(userId)
//        System.out.println(response.toString())
//
//        assert(response?.size != 0)
//    }
//
//    @Test
//    fun deletePaymentCardById(){
//        val response = paymentService.deletePaymentCard(5)
//        assertEquals("Payment card deleted!!", response.cardHolderName)
//    }
//
//    @Test
//    fun createNewInterview(){
//
//        val interview = InterviewInput(
//            type = "voice",
//            difficulty = 8.0f,
//            userId = 25
//        )
//
//        val response = interviewService.createInterview(interview)
//
//        assert(response.id != null)
//
//    }
//
//    @Test
//    fun getInterviewsByUserId(){
//        val userId = 25L;
//
//        val response = interviewService.getLastInterviews(userId)
//
//        System.out.println(response.toString())
//
//        assert(response?.size != 0 && response!!.isNotEmpty())
//    }
//
//    @Test
//    fun getResultByInterviewId(){
//        val userId = 4L;
//
//        val response = interviewService.getResultsByInterviewId(userId)
//
//        System.out.println(response.toString())
//
//        assert(response?.id != null)
//    }
//
//    @Test
//    fun getAchievementsByUser(){
//        val userId = 25L;
//
//        val response = achievementService.getAchievementByUser(userId)
//
//        System.out.println(response.toString())
//
//        assert(response!!.isNotEmpty())
//
//    }
//
//    @Test
//    fun addAchievement(){
//        val achievement = AchievementDTO(
//            title = "Practicante",
//            description = "Completa 10 entrevistas con nuestra app",
//        )
//
//        val response = achievementService.addAchievement(achievement)
//
//        System.out.println(response.toString())
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun associateAchievementWithUser(){
//        val userAchievement = UserAchivementDTO(
//            userId = 25,
//            achievementId = 9L,
//        )
//
//        val response = achievementService.associateAchievementWithUser(userAchievement)
//
//        assertEquals(response.description, "Relation added successfully!!")
//    }
//
//    @Test
//    fun getOtherAchievements(){
//        val userId = 25L;
//
//        val response = achievementService.getOtherAchievements(userId)
//
//        System.out.println(response.toString())
//
//        assert(response!!.isNotEmpty())
//    }
//
//    @Test
//    fun addCompanyManagerWithSocialNetwork(){
//        val company = CompanyInput(
//            legalId = "A123876cd1245",
//            webSite = "https://www.microsoft.com/es-cr/costa-rica",
//            email = "apptester-${java.util.UUID.randomUUID()}@mycompany.com",
//            name = "Microsoft",
//            description = "The biggest technology company on the world.....",
//            industry = "Technology and innovation",
//            user = UserInput(
//                firstName = "Kassandra",
//                lastName = "Valverde Mejia",
//                email = "apptester-${java.util.UUID.randomUUID()}@mycompany.com",
//                enabled = true,
//                password = "OtraPassword123#$",
//                tokenExpired = false,
//                aboutUser = "Manager for Microsoft company",
//                suscription = 2,
//                socialNetwork = mutableListOf(
//                    SocialNetworkDTO(
//                        name = "Facebook",
//                        link = "https://www.facebook.com/microsoftOfficial",
//                    )
//                ),
//            ),
//            location = LocationDTO(
//                country = "Costa Rica",
//                city = "San Jose",
//                address = "PLAZA RIVIERA, S.A., Complejo Avenida Escazú, contiguo al Hospital Cima, Edificio AE205, en parte del piso 2 y en el piso 4 completo, San José."
//            )
//        )
//
//        val response = companyService.addCompany(company)
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun addCompanyWithMoreSocialNetwork(){
//        val company = CompanyInput(
//            legalId = "A123876cdo245",
//            webSite = "https://www.microsoft.com/es-cr/costa-rica",
//            email = "apptester-${java.util.UUID.randomUUID()}@mycompany.com",
//            name = "Amazon",
//            description = "The second biggest technology company on the world.....",
//            industry = "Technology and innovation",
//            user = UserInput(
//                firstName = "Carlos",
//                lastName = "Garita",
//                email = "apptester-${java.util.UUID.randomUUID()}@mycompany.com",
//                enabled = true,
//                password = "OtraPassword123#$",
//                tokenExpired = false,
//                aboutUser = "Manager for Amazon company",
//                suscription = 2,
//                socialNetwork = mutableListOf(
//                    SocialNetworkDTO(
//                        name = "Facebook",
//                        link = "https://www.facebook.com/microsoftOfficial",
//                    ),
//                    SocialNetworkDTO(
//                        name = "Instagram",
//                        link = "https://www.instagram.com/microsoftOfficial",
//                    )
//                )
//            ),
//            location = LocationDTO(
//                country = "Costa Rica",
//                city = "San Jose",
//                address = "PLAZA RIVIERA, S.A., bla bla bla 222."
//            )
//        )
//
//        val response = companyService.addCompany(company)
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun addCompanyWithOutSocialNetworks(){
//        val company = CompanyInput(
//            legalId = "A123876cdo245",
//            webSite = "https://www.microsoft.com/es-cr/costa-rica",
//            email = "apptester-${java.util.UUID.randomUUID()}@mycompany.com",
//            name = "Amazon",
//            description = "The second biggest technology company on the world.....",
//            industry = "Technology and innovation",
//            user = UserInput(
//                firstName = "Carlos",
//                lastName = "Garita",
//                email = "apptester-${java.util.UUID.randomUUID()}@mycompany.com",
//                enabled = true,
//                password = "OtraPassword123#$",
//                tokenExpired = false,
//                aboutUser = "Manager for Amazon company",
//                suscription = 2,
//            ),
//            location = LocationDTO(
//                country = "Costa Rica",
//                city = "San Jose",
//                address = "PLAZA RIVIERA, S.A., bla bla bla."
//            )
//        )
//
//        val response = companyService.addCompany(company)
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun findCompanyById(){
//        val companyId = 9L
//
//        val response = companyService.finById(companyId)
//
//        System.out.println(response.toString())
//        System.out.println(response?.user?.socialNetwork)
//
//        assert(response?.id != null)
//    }
//
//    @Test
//    fun loginWithUserAccound(){
//        val credential = CredentialInput(
//            email = "apptester-2fd4c488-33e0-487d-99cb-75b1548c8045@example.com",
//            password = "MyOtherNotPassword123#$"
//        )
//
//        val response = authenticationService.login(credential)
//
//        System.out.println(response.toString())
//
//        assert(response != null)
//    }
//
//    @Test
//    fun deleteAcoound(){
//        val userId = 4L
//
//        val response = userService.deleteAccound(userId)
//        System.out.println(response.toString())
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun loginWithManagerAccound(){
//        val credential = CredentialInput(
//            email = "apptester-2fd4c488-33e0-487d-99cb-75b1548c8045@example.com",
//            password = "OtraPassword123#$"
//        )
//
//        val response = authenticationService.login(credential)
//
//        System.out.println(response.toString())
//
//        assert(response != null)
//    }
//
//    @Test
//    fun loginWithInvalidAccound(){
//        val credential = CredentialInput(
//            email = "apptester-5e5ff842-c652-4609-8407-1a1896e32dd0@mycompny.com",
//            password = "OtraPassword123#"
//        )
//
//        val response = authenticationService.login(credential)
//
//        System.out.println(response.toString())
//
//        assert(response != null)
//    }
//
//    @Test
//    fun loginWithNotEnabledAccound(){
//        val credential = CredentialInput(
//            email = "apptester-c28e1adf-10c0-4342-9974-470290bcc6db@example.com",
//            password = "MyPassword123#$"
//        )
//
//        val response = authenticationService.login(credential)
//
//        System.out.println(response.toString())
//
//        assert(response != null)
//    }
//
//    @Test
//    fun addJob(){
//        val job = JobInput(
//            title = "Software development Senior",
//            workMode = "Virtual",
//            description = "bla, bla, bla",
//            jobUrl = "https://www.microsoft.com/myjobs/",
//            companyId = 11,
//            shift = "sadasdasda"
//        )
//
//        val response = jobService.addJob(job)
//
//        System.out.println(response.toString())
//
//        assert(response.id != null)
//
//    }
//
//    @Test
//    fun applyJob(){
//        val application = ApplicationDTO(
//            userId = 25L,
//            jobId = 4L,
//        )
//
//        val response = jobService.applyJob(application)
//
//        System.out.println(response.toString())
//
//        assert(response.id != null)
//    }
//
//    @Test
//    fun getJobsPostedByCompany(){
//        val companyId = 11L
//
//        val response = jobService.getJobsPostedByCompany(companyId)
//        System.out.println(response)
//
//        assert(response != null)
//    }
//
//    @Test
//    fun getJobsAppliedByUser(){
//        val userId = 25L
//
//        val response = jobService.getLastJobApplicationsByUser(userId)
//        System.out.println(response)
//
//        assert(response != null)
//    }
//
//    @Test
//    fun findJobsByParam(){
//        val params = "Software"
//
//        val response = jobService.searchJobByParam(params)
//        System.out.println(response.toString())
//        assert(response != null)
//    }
//}
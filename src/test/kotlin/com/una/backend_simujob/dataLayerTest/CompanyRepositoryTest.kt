//package com.una.backend_simujob.dataLayerTest
//
//import com.una.backend_simujob.data.entity.Company
//import com.una.backend_simujob.data.entity.User
//import com.una.backend_simujob.data.repository.CompanyRepository
//import com.una.backend_simujob.data.repository.UserRepository
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.transaction.annotation.Transactional
//import java.sql.Date
//import kotlin.test.assertEquals
//import kotlin.test.assertFalse
//import kotlin.test.assertNotNull
//
//@SpringBootTest
//@Transactional
//class CompanyRepositoryTest(
//    @Autowired
//    val userRepository: UserRepository,
//
//    @Autowired
//    val companyRepository: CompanyRepository
//) {
//
//    @Test
//    fun readCompanyById() {
//        val user = User(
//            firstName = "Reader",
//            lastName = "User",
//            email = "reader@example.com",
//            enabled = true,
//            password = "ReaderPass123",
//            tokenExpired = false,
//            aboutUser = "Reader user",
//            curriculum = null,
//            suscription = 2,
//            createDate = Date(125, 4, 20)
//        )
//        val savedUser = userRepository.save(user)
//
//        val company = Company(
//            legalId = "0987654321",
//            name = "ReadCorp",
//            email = "info@readcorp.com",
//            webSite = "https://readcorp.com",
//            industry = "Education",
//            description = "A reading company",
//            user = savedUser
//        )
//        val savedCompany = companyRepository.save(company)
//
//        val found = companyRepository.findById(savedCompany.id!!).get()
//        assertEquals(company, found)
//    }
//
//    @Test
//    fun updateCompany() {
//        val user = User(
//            firstName = "Updater",
//            lastName = "User",
//            email = "updater@example.com",
//            enabled = true,
//            password = "Update123!",
//            tokenExpired = false,
//            aboutUser = "Updater user",
//            curriculum = null,
//            suscription = 3,
//            createDate = Date(125, 4, 20)
//        )
//        val savedUser = userRepository.save(user)
//
//        val company = Company(
//            legalId = "5555555555",
//            name = "OldName",
//            email = "old@example.com",
//            webSite = "https://oldsite.com",
//            industry = "Retail",
//            description = "Old description",
//            user = savedUser
//        )
//        val savedCompany = companyRepository.save(company)
//
//        // Update fields
//        savedCompany.name = "NewName"
//        savedCompany.email = "new@example.com"
//        savedCompany.description = "New description"
//
//        val updated = companyRepository.save(savedCompany)
//        assertEquals("NewName", updated.name)
//        assertEquals("new@example.com", updated.email)
//        assertEquals("New description", updated.description)
//    }
//
//    @Test
//    fun deleteCompanyById() {
//        val user = User(
//            firstName = "Deleter",
//            lastName = "User",
//            email = "deleter@example.com",
//            enabled = true,
//            password = "Delete123!",
//            tokenExpired = false,
//            aboutUser = "Deleter",
//            curriculum = null,
//            suscription = 1,
//            createDate = Date(125, 4, 20)
//        )
//        val savedUser = userRepository.save(user)
//
//        val company = Company(
//            legalId = "2222222222",
//            name = "DeleteCorp",
//            email = "delete@corp.com",
//            webSite = "https://deletecorp.com",
//            industry = "Services",
//            description = "To be deleted",
//            user = savedUser
//        )
//        val savedCompany = companyRepository.save(company)
//
//        companyRepository.deleteById(savedCompany.id!!)
//        val exists = companyRepository.findById(savedCompany.id!!).isPresent
//        assertFalse(exists)
//    }
//
//    @Test
//    fun associateCompanyWithUser() {
//        val user = User(
//            firstName = "Assoc",
//            lastName = "User",
//            email = "assoc@example.com",
//            enabled = true,
//            password = "Assoc123!",
//            tokenExpired = false,
//            aboutUser = "Assoc user",
//            curriculum = null,
//            suscription = 2,
//            createDate = Date(125, 4, 20)
//        )
//        val savedUser = userRepository.save(user)
//
//        val company = Company(
//            legalId = "3333333333",
//            name = "AssocCorp",
//            email = "assoc@corp.com",
//            webSite = "https://assoccorp.com",
//            industry = "Finance",
//            description = "Association test",
//            user = savedUser
//        )
//        val savedCompany = companyRepository.save(company)
//
//        // Verify bidirectional relationship
//        val owner = companyRepository.findById(savedCompany.id!!).get().user
//        assertNotNull(owner)
//        assertEquals(savedUser.id, owner!!.id)
//    }
//}

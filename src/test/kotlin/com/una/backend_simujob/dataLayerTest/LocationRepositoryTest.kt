//package com.una.backend_simujob.dataLayerTest
//
//import com.una.backend_simujob.data.entity.Location
//import com.una.backend_simujob.data.entity.Company
//import com.una.backend_simujob.data.entity.User
//import com.una.backend_simujob.data.repository.LocationRepository
//import com.una.backend_simujob.data.repository.CompanyRepository
//import com.una.backend_simujob.data.repository.UserRepository
//import org.springframework.transaction.annotation.Transactional
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import java.sql.Date
//import kotlin.test.assertEquals
//import kotlin.test.assertFalse
//import kotlin.test.assertNotNull
//
//@SpringBootTest
//@Transactional
//class LocationRepositoryTest(
//    @Autowired
//    val userRepository: UserRepository,
//
//    @Autowired
//    val companyRepository: CompanyRepository,
//
//    @Autowired
//    val locationRepository: LocationRepository,
//){
//
//    @Test
//    fun insertLocation() {
//        val user = User(
//            firstName = "LocUser",
//            lastName = "Tester",
//            email = "loctester-\${UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "LocPass123",
//            tokenExpired = false,
//            aboutUser = "Location test",
//            curriculum = null,
//            suscription = 1,
//            createDate = Date(125, 4, 20)
//        )
//        val savedUser = userRepository.save(user)
//
//        val company = Company(
//            legalId = "4444444444",
//            name = "LocCorp",
//            email = "contact@loccorp.com",
//            webSite = "https://loccorp.com",
//            industry = "Logistics",
//            description = "Location test company",
//            user = savedUser
//        )
//        val savedCompany = companyRepository.save(company)
//
//        val location = Location(
//            company = savedCompany,
//            country = "Costa Rica",
//            city = "San José",
//            address = "123 Main St"
//        )
//        val savedLocation = locationRepository.save(location)
//
//        assertNotNull(savedLocation.companyId)
//        assertEquals(savedCompany.id, savedLocation.companyId)
//        assertEquals("San José", savedLocation.city)
//    }
//
//    @Test
//    fun readLocationById() {
//        // reuse insert logic
//        insertLocation()
//        val id = locationRepository.findAll().first().companyId!!
//        val found = locationRepository.findById(id).get()
//        assertEquals("Costa Rica", found.country)
//    }
//
//    @Test
//    fun updateLocation() {
//        insertLocation()
//        val savedLocation = locationRepository.findAll().first()
//        savedLocation.country = "USA"
//        savedLocation.city = "San Francisco"
//        savedLocation.address = "456 Market St"
//
//        val updated = locationRepository.save(savedLocation)
//        assertEquals("USA", updated.country)
//        assertEquals("San Francisco", updated.city)
//        assertEquals("456 Market St", updated.address)
//    }
//
//    @Test
//    fun deleteLocationById() {
//        insertLocation()
//        val id = locationRepository.findAll().first().companyId!!
//        locationRepository.deleteById(id)
//        val exists = locationRepository.findById(id).isPresent
//        assertFalse(exists)
//    }
//
//    @Test
//    fun associateLocationWithCompany() {
//        insertLocation()
//        val savedLocation = locationRepository.findAll().first()
//        val company = companyRepository.findById(savedLocation.companyId!!).get()
//        assertNotNull(company)
//        assertEquals(savedLocation.companyId, company.id)
//    }
//}

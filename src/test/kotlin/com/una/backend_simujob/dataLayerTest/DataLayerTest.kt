//package com.una.backend_simujob.dataLayerTest
//
//import com.una.backend_simujob.data.embedded.RolePrivilegeId
//import com.una.backend_simujob.data.embedded.UserAchievementId
//import com.una.backend_simujob.data.embedded.UserRoleId
//import com.una.backend_simujob.data.entity.*
//import com.una.backend_simujob.data.repository.*
//import jakarta.transaction.Transactional
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.annotation.Rollback
//import java.sql.Date
//import kotlin.test.assertEquals
//import kotlin.test.assertFalse
//import kotlin.test.assertNotNull
//import kotlin.test.assertTrue
//
//
//@SpringBootTest
//class DataLayerTest(
//    @Autowired
//    val userRepository: UserRepository,
//
//    @Autowired
//    val interviewRepository: InterviewRepository,
//
//    @Autowired
//    val makeRepository: MakeRepository,
//
//    @Autowired
//    val educationRepository: educationRepository,
//
//    @Autowired
//    val userRoleRepository: userRoleRepository,
//
//    @Autowired
//    val roleRepository: roleRepository,
//
//    @Autowired
//    val rolePrivilegeRepository: rolePrivilegeRepository,
//
//    @Autowired
//    val privilegeRepository: privilegeRepository,
//
//    @Autowired
//    val userAchievementRepository: userAchievementRepository,
//
//    @Autowired
//    val achievementRepository: achievementRepository,
//
//    @Autowired
//    val paymentCardRepository: paymentCardRepository,
//){
//
//    private fun createAndSaveUser(): User{
//        val user = User(
//            firstName = "User test name",
//            lastName = "User test lastnames",
//            email = "apptester-${java.util.UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "MyPassword123#$",
//            tokenExpired = false,
//            aboutUser = "User about test...",
//            curriculum = null,
//            suscription = 2,
//            createDate = java.util.Date()
//        )
//
//        return userRepository.save(user)
//    }
//
//    private fun createAndSaveUserWithEducation(): User{
//        val user = User(
//            firstName = "User test name",
//            lastName = "User test lastnames",
//            email = "apptester-${java.util.UUID.randomUUID()}@example.com",
//            enabled = true,
//            password = "MyPassword123#$",
//            tokenExpired = false,
//            aboutUser = "User about test...",
//            curriculum = null,
//            suscription = 2,
//            createDate = Date(125, 4, 20)
//        )
//
//        val edu1 = Education(
//            degree = "Bachelor",
//            institutionName = "Universidad Nacional de Costa Rica",
//            user = user
//        )
//        val edu2 = Education(
//            degree = "Linux Essentials",
//            institutionName = "Cisco",
//            user = user
//        )
//
//        user.education!!.add(edu1)
//        user.education!!.add(edu2)
//
//        return userRepository.save(user)
//    }
//
//    private fun createAndSaveInterview(): Interview{
//        val interview = Interview(
//            type = "voice",
//            difficulty = 6.7f
//        )
//        return interviewRepository.save(interview)
//    }
//
//    private fun createAndSaveUserInterviewRelation(user: User, interview: Interview) : Make {
//        val make = Make(
//            id = MakeId(userId = user.id, interviewId = interview.id),
//            makeUser = user,
//            makeInterview = interview,
//            interviewDate = Date(125, 4, 20)
//        )
//
//        return makeRepository.save(make)
//    }
//
//    private fun createInterviewResults(interview: Interview): InterviewResult{
//        return InterviewResult(
//            score = 9.0f,
//            feedback = "Maybe you can improve...",
//            interview = interview
//        )
//    }
//
//    private fun createPaymentCard(user: User): PaymentCard{
//        return PaymentCard(
//            cardHolder = "Daniel Moreira F.",
//            cardNumber = "2388240882123227",
//            code = "102",
//            user = user,
//            expirationDate = Date(125, 4, 20)
//        )
//    }
//
//    private fun createUserRole(user: User, role: Role): UserRole{
//        return UserRole(
//            id = UserRoleId(user.id!!, role.id!!),
//            user = user,
//            role = role,
//        )
//    }
//
//    @Test
//    fun insertUserWithOutEducation() {
//        val response = createAndSaveUser()
//        assert(response.id != null)
//    }
//
//    @Test
//    fun readUserByIdFromDatabase() {
//        val user = createAndSaveUser();
//        val userDB = userRepository.save(user)
//        val response = userRepository.findById(userDB.id!!).get()
//        assertEquals(user, response)
//    }
//
//    @Test
//    fun updateUserDataById(){
//        val user = createAndSaveUser();
//        user.firstName = "Carlos Andres"
//        user.suscription = 1
//        val updateResponse = userRepository.save(user)
//        assertEquals(updateResponse.firstName, "Carlos Andres")
//        assertEquals(updateResponse.suscription, 1)
//    }
//
//    @Test
//    fun deleteUserById() {
//        val user = createAndSaveUser()
//        userRepository.deleteById(user.id!!)
//        val exists = userRepository.findById(user.id!!).isPresent
//        assertFalse(exists)
//    }
//
//    @Transactional
//    @Rollback(false)
//    @Test
//    fun insertUserWithEducation(){
//        val response = createAndSaveUserWithEducation()
//        assert(response.id != null)
//        assert(response.education!!.size == 2)
//    }
//
//    @Test
//    fun deleteUserWithEducation(){
//        val user = createAndSaveUserWithEducation()
//        userRepository.deleteById(user.id!!)
//        val exists = userRepository.findById(user.id!!).isPresent
//        assertFalse(exists)
//    }
//
//    @Test
//    fun deleteEducationAssociatedWithUser(){
//        val user = createAndSaveUserWithEducation()
//        val eduToDelete = user.education!!.first()
//        educationRepository.deleteById(eduToDelete.id!!)
//        val exist = educationRepository.findById(eduToDelete.id!!).isPresent
//        assertFalse(exist)
//    }
//
//    @Test
//    fun createUserAndInterviewAndAssociateThem(){
//        val user = createAndSaveUser()
//        val interview = createAndSaveInterview()
//        val relation = createAndSaveUserInterviewRelation(user, interview)
//        assert(relation.id.userId != null && relation.id.interviewId != null)
//    }
//
//    @Test
//    fun deleteUserAndInterviewRelation(){
//        val user = createAndSaveUser()
//        val interview = createAndSaveInterview()
//        val relation = createAndSaveUserInterviewRelation(user, interview)
//        makeRepository.deleteById(relation.id)
//        val exists = makeRepository.findById(relation.id).isPresent
//        assertFalse(exists)
//    }
//
//    @Test
//    fun addResultsToInterview(){
//        val user = createAndSaveUser()
//        val interview = createAndSaveInterview()
//        createAndSaveUserInterviewRelation(user, interview)
//        val result = createInterviewResults(interview)
//        interview.interviewResult = result
//
//        val interviewUpdated = interviewRepository.save(interview)
//        assert(interviewUpdated.interviewResult != null)
//    }
//
//    @Test
//    fun deleteInterviewAssociatedWithUser(){
//        val user = createAndSaveUser()
//        val interview = createAndSaveInterview()
//        createAndSaveUserInterviewRelation(user, interview)
//
//        interviewRepository.deleteById(interview.id!!)
//        val exist = interviewRepository.findById(interview.id!!).isPresent
//        assertFalse(exist)
//    }
//
//    @Transactional
//    @Rollback(false)
//    @Test
//    fun addPaymentCardToUser(){
//        val user = createAndSaveUser()
//        val card = createPaymentCard(user)
//        user.paymentCards?.add(card)
//        val savedUser = userRepository.save(user)
//
//        val retrievedUser = userRepository.findById(savedUser.id!!).get()
//        val retrievedCards = retrievedUser.paymentCards
//
//        assertNotNull(retrievedCards)
//        assertEquals(1, retrievedCards!!.size)
//        assertEquals("Daniel Moreira F.", retrievedCards!!.toList()[0].cardHolder)
//    }
//
//    @Transactional
//    @Rollback(false)
//    @Test
//    fun deleteUserAndVerifyCardDeletion() {
//        val user = createAndSaveUser()
//        val card = createPaymentCard(user)
//        user.paymentCards?.add(card)
//        val savedUser = userRepository.save(user)
//
//        val cardToDelete = savedUser.paymentCards!!.first()
//        val retrievedUser = userRepository.findById(savedUser.id!!).get()
//        val retrievedCards = retrievedUser.paymentCards
//
//        assertNotNull(retrievedCards)
//        assertEquals(1, retrievedCards!!.size)
//        assertEquals("Daniel Moreira F.", cardToDelete.cardHolder)
//
//        userRepository.deleteById(savedUser.id!!)
//
//        val deletedUser = userRepository.findById(savedUser.id!!)
//        assertFalse(deletedUser.isPresent)
//
//        val deletedCard = paymentCardRepository.findById(cardToDelete.id!!)
//        assertFalse(deletedCard.isPresent)
//    }
//
//    @Test
//    fun addARole(){
//        var role = Role(
//            name = "Company"
//        )
//        var idRole = roleRepository.save(role).id
//        var retrieveRole = roleRepository.findById(idRole!!).get()
//        assertEquals(role.name, retrieveRole.name)
//    }
//
//    @Transactional
//    @Rollback(false)
//    @Test
//    fun addRoleToUser(){
//        val user = createAndSaveUser()
//        val role = Role(name = "Professional")
//        var roleDB = roleRepository.save(role)
//        var relation = createUserRole(user, roleDB)
//        var relationDB = userRoleRepository.save(relation)
//        assertTrue(relationDB.id.userId != null && relationDB.id.roleId != null)
//    }
//
//    @Test
//    fun createAchievement(){
//        var achievement = Achievement(
//            title = "Rey del Verso Profesional",
//            description = "Pasa con mas del 90% de calificacion, cinco simulaciones de entrevista de manera seguida"
//        )
//
//        val achivementDB = achievementRepository.save(achievement)
//
//        assertEquals(achivementDB.title, achievement.title)
//    }
//
//    @Transactional
//    @Rollback(false)
//    @Test
//    fun addAchievementUser(){
//        val user = createAndSaveUser()
//        val achievement = Achievement(
//            title = "Rey del Verso Profesional",
//            description = "Pasa con mas del 90% de calificacion, cinco simulaciones de entrevista de manera seguida"
//        )
//        val achievementDB = achievementRepository.save(achievement)
//
//        val relation = UserAchievement(
//            id = UserAchievementId(user.id!!, achievementDB.id!!),
//            user = user,
//            achievement = achievement
//        )
//        val relationDB = userAchievementRepository.save(relation)
//        assertTrue(relationDB.id.userId != null && relationDB.id.achievementId != null)
//    }
//
//    @Test
//    fun createPrivilege(){
//        val privilege = Privilege(
//            name = "Make Job offer"
//        )
//        val privilegeDB = privilegeRepository.save(privilege)
//        assertEquals(privilegeDB.name, privilege.name)
//    }
//
//    @Transactional
//    @Rollback(false)
//    @Test
//    fun addPrivilegeToRole(){
//        val role = Role(name = "Professional")
//
//        val privilege = Privilege(name = "Actionate Voice Interview")
//        var roleDB = roleRepository.save(role)
//        var privilegeDB = privilegeRepository.save(privilege)
//
//        var relation = RolePrivilege(
//            id = RolePrivilegeId(roleDB.id!!, privilegeDB.id!!),
//            role = role,
//            privilege = privilege,
//        )
//
//        var relationDB = rolePrivilegeRepository.save(relation)
//
//        assertTrue(relationDB.id.roleId != null && relationDB.id.privilegeId != null)
//    }
//
//}
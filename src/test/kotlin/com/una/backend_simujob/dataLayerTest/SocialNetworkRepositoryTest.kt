package com.una.backend_simujob.dataLayerTest

import com.una.backend_simujob.data.entity.SocialNetwork
import com.una.backend_simujob.data.entity.User
import com.una.backend_simujob.data.repository.SocialNetworkRepository
import com.una.backend_simujob.data.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.sql.Date
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringBootTest
class SocialNetworkRepositoryTest(
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val socialRepo: SocialNetworkRepository,
){

    private fun createUser(): User {
        val user = User(
            firstName = "Saul",
            lastName = "Araya",
            email = "saularayasalas10@gmail.com",
            enabled = true,
            password = "Caliope10123#",
            tokenExpired = false,
            aboutUser = "Final year student.....",
            curriculum = null,
            suscription = 1,
            createDate = Date(125, 4, 20)
        )
        return userRepository.save(user)
    }

    @Test
    fun insertSocialNetwork() {
       val user = createUser()
        System.out.println(user)
        assertEquals(user.firstName, "Saul")
    }

    @Test
    fun readSocialNetworkById() {
        insertSocialNetwork()
        val all = socialRepo.findAll()
        assertTrue(all.isNotEmpty())
        val found = socialRepo.findById(all.first().id!!).get()
        assertEquals(all.first().id, found.id)
    }

    @Test
    fun updateSocialNetwork() {
        val user = createUser()
        val sn = SocialNetwork(
            socialNetworkName = "Twitter",
            link = "https://twitter.com/test",
            user = user
        )
        val saved = socialRepo.save(sn)
        saved.link = "https://twitter.com/updated"
        val updated = socialRepo.save(saved)
        assertEquals("https://twitter.com/updated", updated.link)
    }

    @Test
    fun deleteSocialNetwork() {
        insertSocialNetwork()
        val all = socialRepo.findAll()
        assertTrue(all.isNotEmpty())
        socialRepo.delete(all.first())
        assertTrue(socialRepo.findAll().isEmpty())
    }

    @Test
    fun associateSocialNetworkWithUser() {
        val user = createUser()
        val sn = SocialNetwork(
            socialNetworkName = "GitHub",
            link = "https://github.com/test",
            user = user
        )
        val saved = socialRepo.save(sn)
        val entity = socialRepo.findById(saved.id!!).get()
        assertNotNull(entity.user)
        assertEquals(user.id, entity.user!!.id)
    }
}

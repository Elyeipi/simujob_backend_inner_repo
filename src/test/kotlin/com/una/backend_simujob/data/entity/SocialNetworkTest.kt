package com.una.backend_simujob.data.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.sql.Date

class SocialNetworkTest {

    // Helper to create a minimal User instance
    private fun makeUser(id: Long) = User(
        id = id,
        createDate   = Date.valueOf("2025-01-01"),
        email        = "user$id@example.com",
        enabled      = true,
        firstName    = "First$id",
        lastName     = "Last$id",
        password     = "pass$id",
        tokenExpired = false,
        aboutUser    = "About$id",
        curriculum   = "CV$id",
        suscription  = 1
    )

    @Test
    fun testEquals() {
        val u = makeUser(42L)
        val s1 = SocialNetwork().apply {
            id = 1L
            socialNetworkName = "LinkedIn"
            link = "https://linkedin.com/in/user"
            user = u
        }
        val s2 = SocialNetwork().apply {
            id = 1L
            socialNetworkName = "LinkedIn"
            link = "https://linkedin.com/in/user"
            user = u
        }

        // reflexive
        assertTrue(s1 == s1)
        // symmetric
        assertTrue(s1 == s2)
        assertTrue(s2 == s1)
        // null and different type
        assertFalse(s1.equals(null))
        assertFalse(s1.equals("a string"))
    }

    @Test
    fun testHashCode() {
        val u = makeUser(99L)
        val s1 = SocialNetwork().apply {
            id = 2L
            socialNetworkName = "Twitter"
            link = "https://twitter.com/user"
            user = u
        }
        val s2 = SocialNetwork().apply {
            id = 2L
            socialNetworkName = "Twitter"
            link = "https://twitter.com/user"
            user = u
        }

        assertEquals(s1.hashCode(), s2.hashCode())
    }

    @Test
    fun getId() {
        val sn = SocialNetwork(id = 10L)
        assertEquals(10L, sn.id)
    }

    @Test
    fun setId() {
        val sn = SocialNetwork()
        sn.id = 20L
        assertEquals(20L, sn.id)
    }

    @Test
    fun getSocialNetworkName() {
        val sn = SocialNetwork(socialNetworkName = "GitHub")
        assertEquals("GitHub", sn.socialNetworkName)
    }

    @Test
    fun setSocialNetworkName() {
        val sn = SocialNetwork()
        sn.socialNetworkName = "Facebook"
        assertEquals("Facebook", sn.socialNetworkName)
    }

    @Test
    fun getLink() {
        val sn = SocialNetwork(link = "https://facebook.com/user")
        assertEquals("https://facebook.com/user", sn.link)
    }

    @Test
    fun setLink() {
        val sn = SocialNetwork()
        sn.link = "https://github.com/user"
        assertEquals("https://github.com/user", sn.link)
    }

    @Test
    fun getUser() {
        val u = makeUser(7L)
        val sn = SocialNetwork(user = u)
        assertEquals(u, sn.user)
    }

    @Test
    fun setUser() {
        val sn = SocialNetwork()
        val u = makeUser(8L)
        sn.user = u
        assertEquals(u, sn.user)
    }
}

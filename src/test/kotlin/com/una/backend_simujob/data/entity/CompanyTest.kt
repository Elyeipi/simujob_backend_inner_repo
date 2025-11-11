//package com.una.backend_simujob.data.entity
//
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.Assertions.*
//import java.sql.Date
//
//class CompanyTest {
//
//    private fun makeUser(id: Long) = User(
//        id = id,
//        createDate    = Date.valueOf("2025-01-01"),
//        email         = "user$id@example.com",
//        enabled       = true,
//        firstName     = "First$id",
//        lastName      = "Last$id",
//        password      = "pass$id",
//        tokenExpired  = false,
//        aboutUser     = "About$id",
//        curriculum    = "CV$id",
//        suscription   = 1
//    )
//
//    @Test
//    fun testEquals() {
//        val u = makeUser(42L)
//        val c1 = Company().apply {
//            id = 1L
//            legalId = "LIC123"
//            name = "Acme"
//            email = "info@acme.com"
//            webSite = "https://acme.com"
//            industry = "Manufacturing"
//            description = "An industrial powerhouse"
//            user = u
//        }
//        val c2 = Company().apply {
//            id = 1L
//            legalId = "LIC123"
//            name = "Acme"
//            email = "info@acme.com"
//            webSite = "https://acme.com"
//            industry = "Manufacturing"
//            description = "An industrial powerhouse"
//            user = u
//        }
//
//        // reflexive
//        assertTrue(c1 == c1)
//        // symmetric
//        assertTrue(c1 == c2)
//        assertTrue(c2 == c1)
//        // null and different types
//        assertFalse(c1.equals(null))
//        assertFalse(c1.equals("some string"))
//    }
//
//    @Test
//    fun testHashCode() {
//        val u = makeUser(99L)
//        val c1 = Company().apply {
//            id = 2L
//            legalId = "LIC999"
//            name = "Beta"
//            email = "contact@beta.com"
//            webSite = "https://beta.com"
//            industry = "Tech"
//            description = "A tech innovator"
//            user = u
//        }
//        val c2 = Company().apply {
//            id = 2L
//            legalId = "LIC999"
//            name = "Beta"
//            email = "contact@beta.com"
//            webSite = "https://beta.com"
//            industry = "Tech"
//            description = "A tech innovator"
//            user = u
//        }
//
//        assertEquals(c1.hashCode(), c2.hashCode())
//    }
//
//    @Test
//    fun getId() {
//        val company = Company(id = 10L)
//        assertEquals(10L, company.id)
//    }
//
//    @Test
//    fun setId() {
//        val company = Company()
//        company.id = 20L
//        assertEquals(20L, company.id)
//    }
//
//    @Test
//    fun getLegalId() {
//        val company = Company(legalId = "LEGAL123")
//        assertEquals("LEGAL123", company.legalId)
//    }
//
//    @Test
//    fun setLegalId() {
//        val company = Company()
//        company.legalId = "NEWLEGAL"
//        assertEquals("NEWLEGAL", company.legalId)
//    }
//
//    @Test
//    fun getName() {
//        val company = Company(name = "MyCo")
//        assertEquals("MyCo", company.name)
//    }
//
//    @Test
//    fun setName() {
//        val company = Company()
//        company.name = "YourCo"
//        assertEquals("YourCo", company.name)
//    }
//
//    @Test
//    fun getEmail() {
//        val company = Company(email = "hello@co.com")
//        assertEquals("hello@co.com", company.email)
//    }
//
//    @Test
//    fun setEmail() {
//        val company = Company()
//        company.email = "sales@co.com"
//        assertEquals("sales@co.com", company.email)
//    }
//
//    @Test
//    fun getWebSite() {
//        val company = Company(webSite = "https://co.com")
//        assertEquals("https://co.com", company.webSite)
//    }
//
//    @Test
//    fun setWebSite() {
//        val company = Company()
//        company.webSite = "https://example.com"
//        assertEquals("https://example.com", company.webSite)
//    }
//
//    @Test
//    fun getIndustry() {
//        val company = Company(industry = "Healthcare")
//        assertEquals("Healthcare", company.industry)
//    }
//
//    @Test
//    fun setIndustry() {
//        val company = Company()
//        company.industry = "Education"
//        assertEquals("Education", company.industry)
//    }
//
//    @Test
//    fun getDescription() {
//        val company = Company(description = "Leading provider")
//        assertEquals("Leading provider", company.description)
//    }
//
//    @Test
//    fun setDescription() {
//        val company = Company()
//        company.description = "Innovative solutions"
//        assertEquals("Innovative solutions", company.description)
//    }
//
//    @Test
//    fun getUser() {
//        val u = makeUser(7L)
//        val company = Company(user = u)
//        assertEquals(u, company.user)
//    }
//
//    @Test
//    fun setUser() {
//        val u = makeUser(8L)
//        val company = Company()
//        company.user = u
//        assertEquals(u, company.user)
//    }
//}

//package com.una.backend_simujob.data.entity
//
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.Assertions.*
//
//class LocationTest {
//
//    // Helper to create a bare‐bones Company
//    //private fun makeCompany(id: Long) = Company(id = id)
//
//    @Test
//    fun testEquals() {
//        val comp = makeCompany(1L)
//        val l1 = Location().apply {
//            companyId = 1L
//            company = comp
//            country = "USA"
//            city = "Metropolis"
//            address = "123 Main St"
//        }
//        val l2 = Location().apply {
//            companyId = 1L
//            company = comp
//            country = "USA"
//            city = "Metropolis"
//            address = "123 Main St"
//        }
//
//        // reflexive
//        assertTrue(l1 == l1)
//        // symmetric
//        assertTrue(l1 == l2)
//        assertTrue(l2 == l1)
//        // null and different type
//        assertFalse(l1.equals(null))
//        assertFalse(l1.equals("some string"))
//    }
//
//    @Test
//    fun testHashCode() {
//        val comp = makeCompany(2L)
//        val l1 = Location().apply {
//            companyId = 2L
//            company = comp
//            country = "Canada"
//            city = "Gotham"
//            address = "456 Elm St"
//        }
//        val l2 = Location().apply {
//            companyId = 2L
//            company = comp
//            country = "Canada"
//            city = "Gotham"
//            address = "456 Elm St"
//        }
//
//        assertEquals(l1.hashCode(), l2.hashCode())
//    }
//
//    @Test
//    fun getCompanyId() {
//        val loc = Location(companyId = 5L)
//        assertEquals(5L, loc.companyId)
//    }
//
//    @Test
//    fun setCompanyId() {
//        val loc = Location()
//        loc.companyId = 10L
//        assertEquals(10L, loc.companyId)
//    }
//
//    @Test
//    fun getCompany() {
//        val comp = makeCompany(7L)
//        val loc = Location(company = comp)
//        assertEquals(comp, loc.company)
//    }
//
//    @Test
//    fun setCompany() {
//        val loc = Location()
//        val comp = makeCompany(8L)
//        loc.company = comp
//        assertEquals(comp, loc.company)
//    }
//
//    @Test
//    fun getCountry() {
//        val loc = Location(country = "Mexico")
//        assertEquals("Mexico", loc.country)
//    }
//
//    @Test
//    fun setCountry() {
//        val loc = Location()
//        loc.country = "Brazil"
//        assertEquals("Brazil", loc.country)
//    }
//
//    @Test
//    fun getCity() {
//        val loc = Location(city = "Star City")
//        assertEquals("Star City", loc.city)
//    }
//
//    @Test
//    fun setCity() {
//        val loc = Location()
//        loc.city = "Central City"
//        assertEquals("Central City", loc.city)
//    }
//
//    @Test
//    fun getAddress() {
//        val loc = Location(address = "789 Oak Ave")
//        assertEquals("789 Oak Ave", loc.address)
//    }
//
//    @Test
//    fun setAddress() {
//        val loc = Location()
//        loc.address = "101 Pine Rd"
//        assertEquals("101 Pine Rd", loc.address)
//    }
//}

//package com.una.backend_simujob.data.entity
//
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import java.time.LocalDateTime
//
//class JobTest {
//
//    private fun makeCompany(id: Long) = Company(id = id)
//
////    @Test
////    fun testEquals() {
////        val fixedDate = LocalDateTime.of(2025, 1, 1, 12, 0)
////
////        val j1 = Job().apply {
////            id = 1L
////            title = "Developer"
////            shift = "Morning"
////            workMode = "Onsite"
////            description = "Write code"
////            jobUrl = "https://jobs.example.com/1"
////            postDate = fixedDate
////            company = makeCompany(10L)
////        }
////        val j2 = Job().apply {
////            id = 1L
////            title = "Developer"
////            shift = "Morning"
////            workMode = "Onsite"
////            description = "Write code"
////            jobUrl = "https://jobs.example.com/1"
////            postDate = fixedDate
////            company = makeCompany(10L)
////        }
////
////        // reflexive
////        assertTrue(j1 == j1)
////        // symmetric
////        assertTrue(j1 == j2)
////        assertTrue(j2 == j1)
////        // null and different types
////        assertFalse(j1.equals(null))
////        assertFalse(j1.equals("some string"))
////    }
//
//    @Test
//    fun testHashCode() {
//        val fixedDate = LocalDateTime.of(2025, 2, 2, 9, 30)
//
//        val j1 = Job().apply {
//            id = 2L
//            title = "Analyst"
//            shift = "Evening"
//            workMode = "Remote"
//            description = "Data analysis"
//            jobUrl = "https://jobs.example.com/2"
//            postDate = fixedDate
//            company = makeCompany(20L)
//        }
//        val j2 = Job().apply {
//            id = 2L
//            title = "Analyst"
//            shift = "Evening"
//            workMode = "Remote"
//            description = "Data analysis"
//            jobUrl = "https://jobs.example.com/2"
//            postDate = fixedDate
//            company = makeCompany(20L)
//        }
//
//        assertEquals(j1.hashCode(), j2.hashCode())
//    }
//
//    @Test
//    fun getId() {
//        val job = Job(id = 42L)
//        assertEquals(42L, job.id)
//    }
//
//    @Test
//    fun setId() {
//        val job = Job()
//        job.id = 99L
//        assertEquals(99L, job.id)
//    }
//
//    @Test
//    fun getTitle() {
//        val job = Job(title = "QA Engineer")
//        assertEquals("QA Engineer", job.title)
//    }
//
//    @Test
//    fun setTitle() {
//        val job = Job()
//        job.title = "DevOps"
//        assertEquals("DevOps", job.title)
//    }
//
//    @Test
//    fun getShift() {
//        val job = Job(shift = "Night")
//        assertEquals("Night", job.shift)
//    }
//
//    @Test
//    fun setShift() {
//        val job = Job()
//        job.shift = "Day"
//        assertEquals("Day", job.shift)
//    }
//
//    @Test
//    fun getWorkMode() {
//        val job = Job(workMode = "Hybrid")
//        assertEquals("Hybrid", job.workMode)
//    }
//
//    @Test
//    fun setWorkMode() {
//        val job = Job()
//        job.workMode = "Onsite"
//        assertEquals("Onsite", job.workMode)
//    }
//
//    @Test
//    fun getDescription() {
//        val job = Job(description = "Lead projects")
//        assertEquals("Lead projects", job.description)
//    }
//
//    @Test
//    fun setDescription() {
//        val job = Job()
//        job.description = "Support team"
//        assertEquals("Support team", job.description)
//    }
//
//    @Test
//    fun getJobUrl() {
//        val job = Job(jobUrl = "https://jobs.example.com/apply")
//        assertEquals("https://jobs.example.com/apply", job.jobUrl)
//    }
//
//    @Test
//    fun setJobUrl() {
//        val job = Job()
//        job.jobUrl = "https://jobs.example.com/info"
//        assertEquals("https://jobs.example.com/info", job.jobUrl)
//    }
//
//    @Test
//    fun getPostDate() {
//        val dt = LocalDateTime.of(2025, 3, 3, 15, 45)
//        val job = Job(postDate = dt)
//        assertEquals(dt, job.postDate)
//    }
//
//    @Test
//    fun setPostDate() {
//        val job = Job()
//        val dt = LocalDateTime.of(2025, 4, 4, 8, 0)
//        job.postDate = dt
//        assertEquals(dt, job.postDate)
//    }
//
//    @Test
//    fun getCompany() {
//        val c = makeCompany(7L)
//        val job = Job(company = c)
//        assertEquals(c, job.company)
//    }
//
//    @Test
//    fun setCompany() {
//        val job = Job()
//        val c = makeCompany(8L)
//        job.company = c
//        assertEquals(c, job.company)
//    }
//}

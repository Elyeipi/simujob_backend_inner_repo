package com.una.backend_simujob.data.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.sql.Date

class JobApplicationTest {

    // Helpers to build minimal User, Company, and Job instances
    private fun makeUser(id: Long) = User(
        id            = id,
        createDate    = Date.valueOf("2025-01-01"),
        email         = "user$id@example.com",
        enabled       = true,
        firstName     = "First$id",
        lastName      = "Last$id",
        password      = "pass$id",
        tokenExpired  = false,
        aboutUser     = "About$id",
        curriculum    = "CV$id",
        suscription   = 1
    )

    //private fun makeCompany(id: Long) = Company(id = id)

    private fun makeJob(id: Long, company: Company, postDate: java.util.Date) = Job().apply {
        this.id = id
        this.title = "Title$id"
        this.shift = "Shift$id"
        this.workMode = "Mode$id"
        this.description = "Description$id"
        this.jobUrl = "https://jobs.example.com/$id"
        this.postDate = postDate
        this.company = company
    }

//    @Test
//    fun testEquals() {
//        val fixedDate = java.util.Date()
//        val user = makeUser(1L)
//        val company = makeCompany(2L)
//        val job = makeJob(2L, company, fixedDate)
//
//        val ja1 = JobApplication().apply {
//            id = JobApplicationKey(userId = 1L, jobId = 2L)
//            this.user = user
//            this.job = job
//            applicationDate = fixedDate
//        }
//        val ja2 = JobApplication().apply {
//            id = JobApplicationKey(userId = 1L, jobId = 2L)
//            this.user = user
//            this.job = job
//            applicationDate = fixedDate
//        }
//
//        // reflexive
//        assertTrue(ja1 == ja1)
//        // symmetric
//        assertTrue(ja1 == ja2)
//        assertTrue(ja2 == ja1)
//        // null and different type
//        assertFalse(ja1.equals(null))
//        assertFalse(ja1.equals("not a JobApplication"))
//    }

//    @Test
//    fun testHashCode() {
//        val fixedDate = LocalDateTime.of(2025, 2, 2, 10, 30)
//        val user = makeUser(3L)
//        val company = makeCompany(4L)
//        val job = makeJob(4L, company, fixedDate)
//
//        val ja1 = JobApplication().apply {
//            id = JobApplicationKey(userId = 3L, jobId = 4L)
//            this.user = user
//            this.job = job
//            applicationDate = fixedDate
//        }
//        val ja2 = JobApplication().apply {
//            id = JobApplicationKey(userId = 3L, jobId = 4L)
//            this.user = user
//            this.job = job
//            applicationDate = fixedDate
//        }
//
//        assertEquals(ja1.hashCode(), ja2.hashCode())
//    }

    @Test
    fun getId() {
        val key = JobApplicationKey(userId = 5L, jobId = 6L)
        val ja = JobApplication(id = key)
        assertEquals(5L, ja.id.userId)
        assertEquals(6L, ja.id.jobId)
    }

    @Test
    fun setId() {
        val ja = JobApplication()
        val key = JobApplicationKey(userId = 7L, jobId = 8L)
        ja.id = key
        assertEquals(7L, ja.id.userId)
        assertEquals(8L, ja.id.jobId)
    }

    @Test
    fun getUser() {
        val user = makeUser(9L)
        val ja = JobApplication(user = user)
        assertEquals(user, ja.user)
    }

    @Test
    fun setUser() {
        val ja = JobApplication()
        val user = makeUser(10L)
        ja.user = user
        assertEquals(user, ja.user)
    }

//    @Test
//    fun getJob() {
//        val fixedDate = LocalDateTime.of(2025, 3, 3, 14, 15)
//        val company = makeCompany(11L)
//        val job = makeJob(11L, company, fixedDate)
//        val ja = JobApplication(job = job)
//        assertEquals(job, ja.job)
//    }
//
//    @Test
//    fun setJob() {
//        val ja = JobApplication()
//        val company = makeCompany(12L)
//        val job = makeJob(12L, company, LocalDateTime.of(2025, 4, 4, 16, 45))
//        ja.job = job
//        assertEquals(job, ja.job)
//    }

//    @Test
//    fun getApplicationDate() {
//        val dt = LocalDateTime.of(2025, 5, 5, 11, 11)
//        val ja = JobApplication(applicationDate = dt)
//        assertEquals(dt, ja.applicationDate)
//    }
//
//    @Test
//    fun setApplicationDate() {
//        val ja = JobApplication()
//        val dt = LocalDateTime.of(2025, 6, 6, 22, 22)
//        ja.applicationDate = dt
//        assertEquals(dt, ja.applicationDate)
//    }
}

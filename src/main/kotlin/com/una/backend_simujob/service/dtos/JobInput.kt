package com.una.backend_simujob.service.dtos

import java.util.Date

data class JobInput(
    val id: Long? = null,
    val title: String,
    val shift: String,
    val workMode: String,
    val description: String,
    val jobUrl: String,
    val postDate: Date? = null,
    val companyId: Long
)
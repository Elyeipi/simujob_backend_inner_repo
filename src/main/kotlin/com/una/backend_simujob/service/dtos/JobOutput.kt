package com.una.backend_simujob.service.dtos

import java.util.*

data class JobOutput(
    val id: Long? = null,
    val title: String,
    val shift: String,
    val workMode: String,
    val description: String,
    val jobUrl: String,
    val postDate: Date,
)

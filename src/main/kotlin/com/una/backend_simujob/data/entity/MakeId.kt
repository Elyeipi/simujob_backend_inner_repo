package com.una.backend_simujob.data.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class MakeId(
    @Column(name = "user_id")
    val userId: Long? = null,

    @Column(name = "interview_id")
    val interviewId: Long? = null,
) : Serializable

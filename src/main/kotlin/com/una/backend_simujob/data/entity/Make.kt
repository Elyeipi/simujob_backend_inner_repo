package com.una.backend_simujob.data.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "make")
data class Make(
    @EmbeddedId
    val id: MakeId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    val makeUser: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("interviewId")
    @JoinColumn(name = "interview_id")
    val makeInterview: Interview,

    @Column(name = "interview_date")
    val interviewDate: Date
)

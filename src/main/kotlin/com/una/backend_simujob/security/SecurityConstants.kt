package com.una.backend_simujob.security

object SecurityConstants {
    const val TOKEN_TYPE = "JWT"
    const val TOKEN_ISSUER = "secure-api"
    const val TOKEN_AUDIENCE = "secure-app"
    const val TOKEN_LIFETIME: Long = 864000000
    const val TOKEN_PREFIX = "Bearer "
    const val APPLICATION_JSON = "application/json"
    const val UTF_8 = "UTF-8"
    const val TOKEN_SECRET: String =
        "======================MikeEducationMikeEducationMikeEducationMikeEducationMikeEducationMikeEducation" +
                "MikeEducation==========================="
}
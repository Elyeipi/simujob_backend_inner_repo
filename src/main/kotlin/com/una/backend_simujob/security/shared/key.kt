package com.una.backend_simujob.security.shared

import com.una.backend_simujob.security.SecurityConstants
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.security.Key

fun key() : Key {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SecurityConstants.TOKEN_SECRET))
}
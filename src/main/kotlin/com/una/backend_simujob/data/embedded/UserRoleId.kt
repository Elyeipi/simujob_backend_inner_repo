package com.una.backend_simujob.data.embedded

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class UserRoleId(
    var userId : Long = 0,
    var roleId : Long = 0
) : Serializable

package com.una.backend_simujob.data.repository

import com.una.backend_simujob.data.entity.SocialNetwork
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SocialNetworkRepository : JpaRepository<SocialNetwork, Long>

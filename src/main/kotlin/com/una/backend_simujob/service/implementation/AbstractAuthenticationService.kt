package com.una.backend_simujob.service.implementation

import com.una.backend_simujob.data.repository.UserRepository
import com.una.backend_simujob.service.dtos.CredentialInput
import com.una.backend_simujob.service.dtos.UserOutput
import com.una.backend_simujob.service.interfaces.AuthenticationService
import com.una.backend_simujob.service.mappers.CompanyMapper
import com.una.backend_simujob.service.mappers.UserMapper
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class AbstractAuthenticationService(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val userMapper: UserMapper,
    @Autowired
    private val companyMapper: CompanyMapper,
    @Autowired
    private val hashService: HashService
) : AuthenticationService {

    override fun login(credentials: CredentialInput): UserOutput? {
        val userEntity = userRepository.findUserByEmailAndEnabled(credentials.email)
            ?: throw Exception("Wrong Credentials or user is not enabled")
        if (!hashService.matches(credentials.password, userEntity.password!!)) {
            System.out.println("Credentials does not match")
            throw Exception("Wrong Credentials")
        }
        return userMapper.userUserEntityToUserOutput(userEntity)
    }

    override fun loadAuthenticateUserData(email: String): UserOutput? {
        System.out.println("ENTRE")
        val userEntity = userRepository.findUserByEmailAndEnabled(email) ?: throw Exception("User is not enabled")
        userEntity.education?.size
        userEntity.socialNetwork?.size
        return userMapper.userUserEntityToUserOutput(userEntity)
    }

    override fun logOut(): String {
        TODO("Not yet implemented")
    } //Here change String

}

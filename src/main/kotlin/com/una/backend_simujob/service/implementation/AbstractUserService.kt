package com.una.backend_simujob.service.implementation

import com.una.backend_simujob.data.entity.User
import com.una.backend_simujob.data.repository.UserRepository
import com.una.backend_simujob.service.dtos.AddNewEducationInput
import com.una.backend_simujob.service.dtos.AddSocialNetworkInput
import com.una.backend_simujob.service.dtos.UserInput
import com.una.backend_simujob.service.dtos.UserOutput
import com.una.backend_simujob.service.interfaces.UserService
import com.una.backend_simujob.service.mappers.UserMapper
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class AbstractUserService(
    @Autowired
    private val userRepository: UserRepository,

    @Autowired
    private val userMapper: UserMapper,

    @Autowired
    private val hashService: HashService
) : UserService {

    override fun addUser(user: UserInput): UserOutput {
        System.out.println(user.toString())
        val userEntity: User = userMapper.userInputToUserEntity(user)
        userEntity.education?.map { edu -> edu.user = userEntity }
        userEntity.socialNetwork?.map { sn -> sn.user = userEntity }
        val hashPassword = hashService.hashPassword(userEntity.password!!)
        userEntity.password = hashPassword

        System.out.println("Llegue hasta aqui")
        val savedUser = userRepository.save(userEntity)
        System.out.println(savedUser.toString())
        return userMapper.userUserEntityToUserOutput(savedUser)
    }

    @Throws(NoSuchElementException::class)
    override fun updateUser(user: UserInput): UserOutput {
        val existingUser = userRepository.findById(user.id!!).orElseThrow {
            NoSuchElementException(String.format("The user with the id: %s not found!", user.id))
        }
        userMapper.matchDatatoEntity(user, existingUser)
        val hashPassword = hashService.hashPassword(user.password)
        existingUser.password = hashPassword
        existingUser.education?.map { edu -> edu.user = existingUser }
        existingUser.socialNetwork?.map { sn -> sn.user = existingUser }
        return userMapper.userUserEntityToUserOutput(userRepository.save(existingUser))
    }

    @Throws(NoSuchElementException::class)
    override fun findById(id: Long): UserOutput? {
        val user = userRepository.findById(id).orElseThrow {
            NoSuchElementException(String.format("The user with the id: %s not found!", id))
        }
        return userMapper.userUserEntityToUserOutput(user)
    }

    override fun analyzeCV(file: String): String? {
        TODO("Not yet implemented")
    }

    @Throws(NoSuchElementException::class)
    override fun deleteAccound(id: Long): UserOutput {
        val user = userRepository.findById(id)
            .orElseThrow { NoSuchElementException(String.format("The user with the id: %s not found!", id)) }
        userRepository.deleteAcoound(id, false)
        user.enabled = false
        return userMapper.userUserEntityToUserOutput(user)
    }

    override fun updateAboutMe(email: String, newAboutMe: String): UserOutput {
        val existingUser = userRepository.findByEmail(email)
            ?: throw NoSuchElementException("User with email $email not found")

        existingUser.aboutUser = newAboutMe

        return userMapper.userUserEntityToUserOutput(userRepository.save(existingUser))
    }

    override fun addNewEducation(email: String, educationInput: AddNewEducationInput): UserOutput? {
        val existingUser = userRepository.findByEmail(email)
            ?: throw NoSuchElementException("User with email $email not found")

        val educationEntity = userMapper.educationDtoToEntity(educationInput.newEducation)

        println("Adding new education for user: ${existingUser.email}")
        println("Education details: $educationEntity")

        educationEntity.user = existingUser

        existingUser.education?.add(educationEntity)

        val updatedUser = userRepository.save(existingUser)
        return userMapper.userUserEntityToUserOutput(updatedUser)
    }

    override fun removeEducation(email: String, educationId: Long): UserOutput? {
        val existingUser = userRepository.findByEmail(email)
            ?: throw NoSuchElementException("User with email $email not found")

        val educationToRemove = existingUser.education?.find { it.id == educationId }
            ?: throw NoSuchElementException("Education with id $educationId not found for user $email")

        existingUser.education?.remove(educationToRemove)

        val updatedUser = userRepository.save(existingUser)
        return userMapper.userUserEntityToUserOutput(updatedUser)
    }

    override fun addNewSocialNetwork(email: String, socialNetworkInput: AddSocialNetworkInput): UserOutput? {
        val existingUser = userRepository.findByEmail(email)
            ?: throw NoSuchElementException("User with email $email not found")

        val socialNetworkEntity = userMapper.socialNetworkDtoToEntity(socialNetworkInput.newSocialNetwork)

        println("Adding new social network for user: ${existingUser.email}")
        println("Social Network details: $socialNetworkEntity")

        socialNetworkEntity.user = existingUser

        existingUser.socialNetwork?.add(socialNetworkEntity)

        val updatedUser = userRepository.save(existingUser)
        return userMapper.userUserEntityToUserOutput(updatedUser)
    }

    override fun removeSocialNetwork(email: String, socialNetworkId: Long): UserOutput? {
        val existingUser = userRepository.findByEmail(email)
            ?: throw NoSuchElementException("User with email $email not found")

        val socialNetworkToRemove = existingUser.socialNetwork?.find { it.id == socialNetworkId }
            ?: throw NoSuchElementException("Social Network with id $socialNetworkId not found for user $email")

        existingUser.socialNetwork?.remove(socialNetworkToRemove)

        val updatedUser = userRepository.save(existingUser)
        return userMapper.userUserEntityToUserOutput(updatedUser)
    }
}

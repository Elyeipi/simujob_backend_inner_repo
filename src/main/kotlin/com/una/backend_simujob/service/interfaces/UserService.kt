package com.una.backend_simujob.service.interfaces

import com.una.backend_simujob.service.dtos.AddNewEducationInput
import com.una.backend_simujob.service.dtos.AddSocialNetworkInput
import com.una.backend_simujob.service.dtos.UserInput
import com.una.backend_simujob.service.dtos.UserOutput

interface UserService { // DO deleteUser method

    fun addUser(user: UserInput): UserOutput //Here change String to DTO

    fun updateUser(user: UserInput) : UserOutput //Here change String to DTO

    fun findById(id: Long): UserOutput? //Here change String to DTO

    fun analyzeCV(file: String) : String? //Here change String to File Type

    fun deleteAccound(id: Long): UserOutput

    fun updateAboutMe(email: String, newAboutMe: String): UserOutput?

    fun addNewEducation(email: String, educationInput: AddNewEducationInput): UserOutput?

    fun removeEducation(email: String, educationId: Long): UserOutput?

    fun addNewSocialNetwork(email: String, socialNetworkInput: AddSocialNetworkInput): UserOutput?

    fun removeSocialNetwork(email: String, socialNetworkId: Long): UserOutput?
}

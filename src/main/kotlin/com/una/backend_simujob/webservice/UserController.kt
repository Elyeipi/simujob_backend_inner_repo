package com.una.backend_simujob.webservice

import com.una.backend_simujob.service.dtos.AddNewEducationInput
import com.una.backend_simujob.service.dtos.AddSocialNetworkInput
import com.una.backend_simujob.service.dtos.UserInput
import com.una.backend_simujob.service.dtos.UserOutput
import com.una.backend_simujob.service.interfaces.UserService
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.users}")
class UserController(private val userService: UserService) {
    @GetMapping("{id}")
    fun findUserById(@PathVariable id: Long) = userService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody userInput: UserInput): UserOutput? {
        return userService.addUser(userInput)
    }

    @PostMapping("signup", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun signup(@RequestBody userInput: UserInput): UserOutput? {
        return userService.addUser(userInput)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody userInput: UserInput): UserOutput? {
        return userService.updateUser(userInput)
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    fun deleteById(@PathVariable id: Long): UserOutput {
        return userService.deleteAccound(id)
    }

    @PutMapping(
        "/updateAboutMe",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun updateAboutMe(
        @RequestParam("newAboutMe") newAboutMe: String,
        authentication: Authentication
    ): UserOutput? {
        val email = authentication.name

        println("Updating about me for user with email: $email")

        return userService.updateAboutMe(email, newAboutMe)
    }

    @PostMapping(
        "/addEducation",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun addEducation(
        @RequestBody educationInput: AddNewEducationInput,
        authentication: Authentication
    ): UserOutput? {
        val email = authentication.name
        println("Adding education for user with email: $email")

        return userService.addNewEducation(email, educationInput)
    }

    @DeleteMapping("/removeEducation/{educationId}")
    @ResponseBody
    fun removeEducation(
        @PathVariable educationId: Long,
        authentication: Authentication
    ): UserOutput? {
        val email = authentication.name
        println("Removing education with ID: $educationId for user with email: $email")

        return userService.removeEducation(email, educationId)
    }

    @PostMapping(
        "/addSocialNetwork",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun addSocialNetwork(
        @RequestBody socialNetworkInput: AddSocialNetworkInput,
        authentication: Authentication
    ): UserOutput? {
        val email = authentication.name
        println("Adding social network for user with email: $email")

        return userService.addNewSocialNetwork(email, socialNetworkInput)
    }

    @DeleteMapping("/removeSocialNetwork/{socialNetworkId}")
    @ResponseBody
    fun removeSocialNetwork(
        @PathVariable socialNetworkId: Long,
        authentication: Authentication
    ): UserOutput? {
        val email = authentication.name
        println("Removing social network with ID: $socialNetworkId for user with email: $email")

        // Assuming the service method is implemented to handle education removal
        return userService.removeSocialNetwork(email, socialNetworkId)
    }
}

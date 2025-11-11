package com.una.backend_simujob.service.dtos

sealed class LoginResultDTO{
    data class UserData(val user: UserOutput): LoginResultDTO()
}


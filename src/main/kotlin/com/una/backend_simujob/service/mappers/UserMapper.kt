package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.Education
import com.una.backend_simujob.data.entity.SocialNetwork
import com.una.backend_simujob.data.entity.User
import com.una.backend_simujob.service.dtos.EducationDTO
import com.una.backend_simujob.service.dtos.SocialNetworkInput
import com.una.backend_simujob.service.dtos.UserInput
import com.una.backend_simujob.service.dtos.UserOutput
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    componentModel = "spring",
    uses = [EducationMapper::class, SocialNetworkMapper::class]
)
interface UserMapper {
    fun userEntityToUserInput(entity: User): UserInput

    @Mapping(target = "createDate", defaultExpression = "java(new java.util.Date())")
    fun userInputToUserEntity (dto: UserInput) : User

    fun userUserEntityToUserOutput(user: User): UserOutput

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun matchDatatoEntity (dto: UserInput, @MappingTarget entity: User)

    fun toDtoList(entities: List<User>): List<UserInput>

    fun educationDtoToEntity(educationDTO: EducationDTO): Education

    fun socialNetworkDtoToEntity(newSocialNetwork: SocialNetworkInput): SocialNetwork
}

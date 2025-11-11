package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.SocialNetwork
import com.una.backend_simujob.service.dtos.SocialNetworkDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface SocialNetworkMapper {

    @Mapping(source = "socialNetworkName", target = "name")
    fun toDto(entity: SocialNetwork): SocialNetworkDTO

    @Mapping(target = "user", ignore = true)
    @Mapping(source = "name", target = "socialNetworkName")
    fun toEntity(dto: SocialNetworkDTO): SocialNetwork

    fun toDtoList(entities: List<SocialNetwork>): List<SocialNetworkDTO>

    fun toEntityList(dtos: MutableList<SocialNetworkDTO>): MutableList<SocialNetwork>
}


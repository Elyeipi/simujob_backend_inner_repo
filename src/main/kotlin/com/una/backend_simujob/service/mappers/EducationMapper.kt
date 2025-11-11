package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.Education
import com.una.backend_simujob.service.dtos.EducationDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface EducationMapper {

    @Mapping(target = "user", ignore = true)
    fun toEntity(dto: EducationDTO): Education

    fun toDto(entity: Education): EducationDTO

    fun toEntityList(dtoList: List<EducationDTO>): List<Education>
    fun toDtoList(entityList: List<Education>): List<EducationDTO>
}
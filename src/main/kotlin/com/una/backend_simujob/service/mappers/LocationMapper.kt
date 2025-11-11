package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.Location
import com.una.backend_simujob.service.dtos.LocationDTO
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface LocationMapper {
    fun locationEntityToLocationDto(entity: Location): LocationDTO
    fun locationDTOToLocationEntity(dto: LocationDTO): Location
    fun toDtoList(entities: List<Location>): List<LocationDTO>
}

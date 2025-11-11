package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.Interview
import com.una.backend_simujob.service.dtos.InterviewInput
import com.una.backend_simujob.service.dtos.InterviewOutput
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [InterviewResultMapper::class])
interface InterviewMapper {

    fun entityInterviewtoInterviewInput(entity: Interview): InterviewInput

    @Mapping(target = "users", ignore = true)
    fun interviewInputToInterviewEntity(dto: InterviewInput): Interview

    @Mapping(target = "date", expression = "java(new java.util.Date())")
    fun interviewEntityToInterviewOutPut(interview: Interview): InterviewOutput

    fun toDtoList(entities: List<Interview>): List<InterviewInput>

    fun entityListToInterviewOutputList(entityList: List<Interview>): List<InterviewOutput>
}

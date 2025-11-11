package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.Make
import com.una.backend_simujob.service.dtos.InterviewOutput
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface MakeMapper {

    @Mapping(source = "makeInterview.id", target = "id")
    @Mapping(source = "makeInterview.type", target = "type")
    @Mapping(source = "makeInterview.difficulty", target = "difficulty")
    @Mapping(source = "interviewDate", target = "date")
    fun makeToInterviewOutput(make: Make): InterviewOutput

    fun makeListToInterviewOutputList(make: List<Make>): List<InterviewOutput>
}
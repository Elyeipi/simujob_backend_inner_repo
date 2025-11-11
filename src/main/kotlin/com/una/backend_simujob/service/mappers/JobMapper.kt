package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.Job
import com.una.backend_simujob.service.dtos.JobCreateDto
import com.una.backend_simujob.service.dtos.JobCreateResponseDto
import com.una.backend_simujob.service.dtos.JobDetails
import com.una.backend_simujob.service.dtos.JobInput
import com.una.backend_simujob.service.dtos.JobOutput
import com.una.backend_simujob.service.dtos.JobSummary
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = [Date::class])
interface JobMapper {
    fun jobEntityToJobOutput(entity: Job): JobOutput

    @Mapping(target = "postDate", defaultExpression = "java(new java.util.Date())")
    fun jobInputToJobEntity(dto: JobInput): Job

    fun jobEntityListtoJobOutputList(entities: List<Job>): List<JobOutput>

    fun jobEntityToJobSummary(entity: Job): JobSummary

    fun jobEntityListToJobSummaryList(entities: List<Job>): List<JobSummary>

    fun jobCreateDtoToJobEntity(dto: JobCreateDto): Job

    fun jobEntityToJobCreateResponseDto(entity: Job): JobCreateResponseDto

    fun jobEntityToJobDetails(entity: Job): JobDetails;
}

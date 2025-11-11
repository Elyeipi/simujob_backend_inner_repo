package com.una.backend_simujob.service.mappers

import com.una.backend_simujob.data.entity.InterviewResult
import com.una.backend_simujob.service.dtos.InterviewResultOutPut
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface InterviewResultMapper {

    fun entityResultToInterviewResultOutPut(entity: InterviewResult): InterviewResultOutPut

    fun resultOutPutToInterviewResult(result: InterviewResultOutPut): InterviewResult
}

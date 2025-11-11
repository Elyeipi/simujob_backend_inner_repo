package com.una.backend_simujob.service.interfaces

import com.una.backend_simujob.service.dtos.FeedbackInput
import com.una.backend_simujob.service.dtos.InterviewInput
import com.una.backend_simujob.service.dtos.InterviewOutput
import com.una.backend_simujob.service.dtos.InterviewResultOutPut


interface InterviewService {

    fun getLastInterviews(userId: Long) : List<InterviewOutput>? //Here change String to Interview

    fun getResultsByInterviewId(interviewId: Long): InterviewResultOutPut?

    fun createInterview(interview: InterviewInput): InterviewOutput //Here change String to Interview

    fun finishInterview(feedbackInput: FeedbackInput) : InterviewResultOutPut
}
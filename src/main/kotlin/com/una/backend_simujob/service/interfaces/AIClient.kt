package com.una.backend_simujob.service.interfaces

import com.una.backend_simujob.service.dtos.*
import org.springframework.web.multipart.MultipartFile

interface AIClient {
    suspend fun chatText(prompt: UserTextPrompt): ChatResponse
    suspend fun feedback(input: FeedbackInput): InterviewAIEvaluation
    suspend fun analyzeCV(file: MultipartFile) : AnalyzedCVResponse
}
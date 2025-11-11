package com.una.backend_simujob.webservice

import com.una.backend_simujob.service.dtos.*
import com.una.backend_simujob.service.implementation.OpenAIService
import kotlinx.coroutines.runBlocking
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("\${url.ai}")
class OpenAIController (private val openAIService: OpenAIService){

    @PostMapping("/chat", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun ask(@RequestBody prompt: UserTextPrompt): ChatResponse {
        return runBlocking { openAIService.chatText(prompt) }
    }

    @PostMapping("/analyze", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun analyze(@RequestPart("file") file: MultipartFile): AnalyzedCVResponse{
        return runBlocking { openAIService.analyzeCV(file) }
    }
}
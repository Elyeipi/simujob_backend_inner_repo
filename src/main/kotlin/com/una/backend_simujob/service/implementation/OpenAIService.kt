package com.una.backend_simujob.service.implementation

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.una.backend_simujob.service.dtos.*
import com.una.backend_simujob.service.interfaces.AIClient
import jakarta.transaction.Transactional
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import java.io.InputStream
import kotlin.jvm.Throws

@Service
@Transactional
class OpenAIService(
    @Value("\${openai.api.key}") private val apiKey: String,
    builder: WebClient.Builder,
): AIClient {
    private val webClient = builder.baseUrl("https://api.openai.com/v1").defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer $apiKey")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build()

    private val conversationHistories = mutableMapOf<String, MutableList<Message>>()

    override suspend fun chatText(prompt: UserTextPrompt): ChatResponse {
        val userHistory = conversationHistories.getOrPut(key = prompt.id.toString()) {  mutableListOf(
            Message(role = "system", content = AIConfiguration.getSystemMessageConfigLevel(prompt.difficulty)))
        }
        userHistory.add(Message("user", prompt.message))
        val request = ChatRequest(messages = userHistory)
        val response = webClient.post().uri("/chat/completions").bodyValue(request).awaitExchange { it.awaitBody<ChatResponse>() }
        val assistantMessage = response.choices?.firstOrNull()?.message
        if (assistantMessage != null) userHistory.add(assistantMessage)
        return response
    }

    @Throws(NoSuchElementException::class)
    override suspend fun feedback(input: FeedbackInput): InterviewAIEvaluation{
        val userHistory = conversationHistories.getOrPut(key = input.id.toString()){  mutableListOf() }
        if(userHistory.isEmpty()) throw NoSuchElementException("User with id: ${input.id}, does not has a interview chat to be evaluated")
        userHistory.add(Message("user", AIConfiguration.getFeedbackPrompt()))
        val response = webClient.post().uri("/chat/completions").bodyValue(ChatRequest(messages = userHistory)).awaitExchange { it.awaitBody<ChatResponse>() }
        val content = response.choices?.first()?.message?.content
        val evaluation = jacksonObjectMapper().readValue(content, InterviewAIEvaluation::class.java)

        conversationHistories.remove(input.id.toString())
        return evaluation
    }

    override suspend fun analyzeCV(file: MultipartFile) : AnalyzedCVResponse {
        System.out.println("ENTRE")
        val text = extractTextFromPdf(file.inputStream)
        val messages: List<Message> = listOf(
            Message(role = "system", content = AIConfiguration.getCVAnalyzePrompt()),
            Message(role = "user", content = text))
        val response = webClient.post().uri("/chat/completions").bodyValue(ChatRequest(messages = messages)).awaitExchange { it.awaitBody<ChatResponse>() }
        return AnalyzedCVResponse(response.choices?.first()?.message?.content ?: "No response by AI")
    }

    private fun extractTextFromPdf(inputStream: InputStream): String {
        PDDocument.load(inputStream).use { document ->
            val stripper = PDFTextStripper()
            return stripper.getText(document)
        }
    }
}
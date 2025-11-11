package com.una.backend_simujob.webservice

import com.una.backend_simujob.service.dtos.*
import com.una.backend_simujob.service.interfaces.AchievementService
import com.una.backend_simujob.service.interfaces.InterviewService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.interviews}")
class InterviewController(private val interviewService: InterviewService, private val achievementService: AchievementService) {
    @GetMapping("users/{id}")
    fun getLastInterviewsByUserId(@PathVariable id : Long) : List<InterviewOutput>?{
        return interviewService.getLastInterviews(id)
    }

    @GetMapping("{id}")
    fun getResultsByInterviewId(@PathVariable id : Long) : InterviewResultOutPut?{
        return interviewService.getResultsByInterviewId(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createInterview(@RequestBody interviewInput: InterviewInput) : InterviewOutput{
        return interviewService.createInterview(interviewInput)
    }

    @PostMapping("/feedback", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun feedbackInterview(@RequestBody input: FeedbackInput): InterviewResultOutPut{
        var interviewRes = interviewService.finishInterview(input)

        return interviewRes
    }
}
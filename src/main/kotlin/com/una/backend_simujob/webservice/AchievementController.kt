package com.una.backend_simujob.webservice

import com.una.backend_simujob.service.dtos.AchievementDTO
import com.una.backend_simujob.service.dtos.UserAchivementDTO
import com.una.backend_simujob.service.interfaces.AchievementService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.achievements}")
class AchievementController(private val achievementService: AchievementService) {
    @GetMapping("{id}")
    fun getAchievementByUserId(@PathVariable id : Long) : List<AchievementDTO>? {
        return achievementService.getAchievementByUser(id)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addAchievement(@RequestBody achievementDTO: AchievementDTO) : AchievementDTO{
        return achievementService.addAchievement(achievementDTO)
    }

    @PostMapping("/users", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun associateAchievementWithUser(@RequestBody userAchivementDTO: UserAchivementDTO) : AchievementDTO{
        return achievementService.associateAchievementWithUser(userAchivementDTO)
    }

    @GetMapping("others/{id}")
    fun getOtherAchievements(@PathVariable id : Long) : List<AchievementDTO>?{
        return achievementService.getOtherAchievements(id)
    }
}
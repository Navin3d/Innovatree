package gmc.project.innovatree.achievements.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.innovatree.achievements.models.AchievementModel;
import gmc.project.innovatree.achievements.service.AchievementsService;
import gmc.project.innovatree.achievements.shared.AchievementsDto;

@RestController
@RequestMapping("/achievement")
public class AchievementsController {
	
	private final AchievementsService achievementsService;

	public AchievementsController(AchievementsService achievementsService) {
		super();
		this.achievementsService = achievementsService;
	}
	
	@GetMapping("/{achievementId}")
	public ResponseEntity<AchievementsDto> showAchievement(@PathVariable String achievementId) {
		AchievementsDto returnValue = achievementsService.findAchievementByAchievementId(achievementId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@PostMapping("/add")
	public ResponseEntity<AchievementsDto> addAchievement(@RequestBody AchievementModel achievementModel) {
		AchievementsDto returnValue = achievementsService.addAchievement(achievementModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<AchievementsDto> editAchievement(@RequestBody AchievementsDto achievementsDto) {
		AchievementsDto returnValue = achievementsService.editAchievement(achievementsDto);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@GetMapping("/{achievementId}/delete")
	public String removeAchievement(@PathVariable String achievementId) {
		achievementsService.deleteAchievement(achievementId);
		return "redirect:/";
	}


}

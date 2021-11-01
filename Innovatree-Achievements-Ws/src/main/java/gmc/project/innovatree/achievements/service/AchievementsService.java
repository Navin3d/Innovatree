package gmc.project.innovatree.achievements.service;

import java.util.List;

import gmc.project.innovatree.achievements.models.AchievementModel;
import gmc.project.innovatree.achievements.shared.AchievementsDto;

public interface AchievementsService {
	AchievementsDto findAchievementByAchievementId(String achievementId);
	AchievementsDto addAchievement(AchievementModel AchievementModel);
	AchievementsDto editAchievement(AchievementsDto achievementsDto);
	void deleteAchievement(String achievementId);
	List<AchievementsDto> findAchievementByUser(String userId);
}

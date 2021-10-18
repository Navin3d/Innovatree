package gmc.project.innovatree.usersws.model;

import lombok.Data;

@Data
public class AchievementsModel {
	
	private String achievementId;	
	private String name;	
	private String imageUrl;
	private String description;
	private Integer plantsCount;

}

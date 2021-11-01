package gmc.project.innovatree.achievements.models;

import lombok.Data;

@Data
public class AchievementModel {
	
	private String name;
	private String description;
	private String imageUrl;
	private Integer plantsCount;

}

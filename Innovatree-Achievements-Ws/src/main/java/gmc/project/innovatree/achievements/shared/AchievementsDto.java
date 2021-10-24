package gmc.project.innovatree.achievements.shared;

import java.io.Serializable;

import lombok.Data;

@Data
public class AchievementsDto implements Serializable {

	private static final long serialVersionUID = -4479373539093486140L;
	
	private String achievementId;
	private String name;
	private String imageUrl;
	private String description;
	private Integer plantsCount;

}

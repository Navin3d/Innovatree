package gmc.project.innovatree.usersws.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class AchievementsEntity implements Serializable {
	
	private static final long serialVersionUID = -2226469925803059022L;
	
	@Id
	@Column(name = "Id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Achievement_Id", nullable = false, unique = true)
	private String achievementId;
	
	private String name;	
	@Lob
	private String imageUrl;
	private String description;
	private Integer plantsCount;

}

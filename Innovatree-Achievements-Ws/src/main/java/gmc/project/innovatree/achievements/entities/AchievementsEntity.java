package gmc.project.innovatree.achievements.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "Achievements")
@Entity
@Data
public class AchievementsEntity implements Serializable {

	private static final long serialVersionUID = -4508726613319889536L;
	
	@Id
	@Column(name = "Id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Achievement_Id", nullable = false, unique = true)
	private String achievementId;
	
	@Column(name = "Name", nullable = false, unique = true)
	private String name;
	
	@Lob
	@Column(name = "Image_URL", nullable = false, unique = true)
	private String imageUrl;
	
	@Column(name = "Description", nullable = false, unique = true)
	private String description;
	
	@Column(name = "No_Of_Plantings")
	private Integer plantsCount;
	
	@JoinColumn(name = "User_Id")
	@ManyToMany
	private Set<UsersEntity> user;
	
	public AchievementsEntity() {
		this.user = new HashSet<>();
	}

}

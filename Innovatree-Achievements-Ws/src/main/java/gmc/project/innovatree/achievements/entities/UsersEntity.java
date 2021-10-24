package gmc.project.innovatree.achievements.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import gmc.project.innovatree.achievements.models.Role;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class UsersEntity implements Serializable {

	private static final long serialVersionUID = 858526429019110135L;
	
	@Id
	@Column(name = "Id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "User_Id", nullable = false, unique = true)
	private String userId;
	
	@Column(name = "Aadhar_Id", unique = true)
	private String aadharNumber;
	
	@Column(name = "First_Name", nullable = false)	
	private String firstName;
	
	@Column(name = "Last_Name", nullable = false)	
	private String lastName;
	
	@Column(name = "No_OF_Plants")
	private Integer plantsCount = 0;
	
	@Column(name = "Walet_Balance")
	private Integer waletBalence = 50;
	
	@Column(name = "Total_Amount_Paid")
	private Integer amountPaid = 0;
	
	@Column(name = "Total_Amount_Earned")
	private Integer amountEarned = 0;
	
	@Column(name = "Email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "Email_Reset_Token", nullable = true, unique = true)
	private String emailResetToken;
	
	@Column(name = "Encrypted_Password", nullable = false)	
	private String encryptedPassword;
	
	@Column(name = "Role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "Is_Enable", nullable = false)
	private boolean isEnable = true;
	
	@Column(name = "Is_Active", nullable = false)
	private boolean isActive = true;
	
	@Column(name = "Phone_Number", nullable = false, unique = true)
	private String phoneNumber;
	
	@Column(name = "Is_Email_Verified", nullable = false)
	private boolean isEmailVerified = false;
	
	@Column(name = "Is_Mobile_No_Verified", nullable = false)
	private boolean isMobileNumberVerified = false;
	
	@ManyToMany
	@JoinColumn(name = "Address_Id")
	private Set<AddressEntity> addressBook;
	
	@ManyToMany
	@JoinColumn(name = "Achievements_Id")
	private Set<AchievementsEntity> achievements;
	
	public UsersEntity() {
		this.addressBook = new HashSet<AddressEntity>();
		this.achievements = new HashSet<AchievementsEntity>();
	}

}

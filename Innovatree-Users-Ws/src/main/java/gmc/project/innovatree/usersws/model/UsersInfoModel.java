package gmc.project.innovatree.usersws.model;

import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Data
public class UsersInfoModel {
	
	private String userId;
	private String firstName;
	private String lastName;
	private String aadharNumber;
	private Role role;
	private Integer plantsCount;
	private String email;
	private String phoneNumber;
	private Integer walletBalence;
	private Integer amountPaid;
	private Integer amountEarned;
	private List<AddressModel> addressBook;
	private List<AchievementsModel> achievements;
	
	public UsersInfoModel() {
		this.addressBook = new ArrayList<AddressModel>();
		this.achievements = new ArrayList<AchievementsModel>();
	}

}

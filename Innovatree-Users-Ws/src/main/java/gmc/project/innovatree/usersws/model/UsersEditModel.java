package gmc.project.innovatree.usersws.model;

import lombok.Data;

@Data
public class UsersEditModel {
	
	private String userId;
	private String firstName;
	private String lastName;
	private String aadharNumber;
	private String email;
	private String phoneNumber;

}

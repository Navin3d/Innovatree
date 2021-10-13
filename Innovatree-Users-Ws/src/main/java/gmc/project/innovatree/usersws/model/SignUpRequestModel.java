package gmc.project.innovatree.usersws.model;

import lombok.Data;

@Data
public class SignUpRequestModel {

	private String firstName;
	private String lastName;
	private String aadharNumber;
	private String email;
	private String password;
	private String phoneNumber;
	
}

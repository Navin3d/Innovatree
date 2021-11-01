package gmc.project.innovatree.auth.model;

import lombok.Data;

@Data
public class SignUpResponseModel {
	
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String aadharNumber;

}

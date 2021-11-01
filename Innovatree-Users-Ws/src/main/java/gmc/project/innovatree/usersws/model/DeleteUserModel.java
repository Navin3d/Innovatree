package gmc.project.innovatree.usersws.model;

import lombok.Data;

@Data
public class DeleteUserModel {
	
	private String userId;
	private String email;
	private String password;

}

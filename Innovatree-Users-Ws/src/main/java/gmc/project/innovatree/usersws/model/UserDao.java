package gmc.project.innovatree.usersws.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDao implements Serializable {
	
	private static final long serialVersionUID = 675129382125666148L;
	
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String phoneNumber;
	private String aadharNumber;
	private Role role;
	private boolean isEnable;
	private boolean isActive;
	
}

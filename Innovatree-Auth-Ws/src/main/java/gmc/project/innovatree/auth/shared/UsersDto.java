package gmc.project.innovatree.auth.shared;

import java.io.Serializable;

import gmc.project.innovatree.auth.model.Role;
import lombok.Data;

@Data
public class UsersDto implements Serializable {

	private static final long serialVersionUID = 3088160278306773608L;
	
	private String userId;
	private String firstName;
	private String lastName;
	private String aadharNumber;
	private Role role;
	private String email;
	private String phoneNumber;
	private boolean isActive;
	private boolean isEnable;

}

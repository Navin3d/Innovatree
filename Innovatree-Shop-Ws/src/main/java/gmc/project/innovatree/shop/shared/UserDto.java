package gmc.project.innovatree.shop.shared;

import java.io.Serializable;

import gmc.project.innovatree.shop.models.Role;
import lombok.Data;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = -6522434951711249513L;
	
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

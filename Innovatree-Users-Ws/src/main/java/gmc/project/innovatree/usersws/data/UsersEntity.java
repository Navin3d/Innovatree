package gmc.project.innovatree.usersws.data;

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
import javax.persistence.Table;


import gmc.project.innovatree.usersws.model.Address;
import gmc.project.innovatree.usersws.model.Role;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class UsersEntity implements Serializable {

	private static final long serialVersionUID = 1148267365553846171L;
	
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
	
	@Column(name = "Email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "Encrypted_Password", nullable = false)	
	private String encryptedPassword;
	
	@Column(name = "Role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "Is_Enable", nullable = false)
	private boolean isEnable;
	
	@Column(name = "Is_Active", nullable = false)
	private boolean isActive;
	
	@Column(name = "Phone_Number", nullable = false, unique = true)
	private String phoneNumber;
	
//	@Column(name = "Address")
//	private Set<Address> addressBook = new HashSet<Address>();
	
//	public UsersEntity() {
//		this.addressBook = new HashSet<Address>();
//	}

}

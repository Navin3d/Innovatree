package gmc.project.innovatree.usersws.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "Address")
@Data
@Entity
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = 2558287257603141359L;
	
	@Id
	@Column(name = "Id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Address_Id", nullable = false, unique = true)
	private String addressId;
	
	@Column(name = "User_Name", nullable = false)
	private String name;
	
	@Column(name = "Door_No", nullable = false)
	private String address;
	
	@Column(name = "City", nullable = false)
	private String city;
	
	@Column(name = "State", nullable = false)
	private String state;
	
	@Column(name = "ZipCode", nullable = false)
	private String zipcode;
	
	@Column(name = "Phone_Number", nullable = false)
	private String phoneNummber;
	
	@ManyToMany
	@JoinColumn(name = "User_Id")
	private Set<UsersEntity> users;
	
	public AddressEntity() {
		this.users = new HashSet<>();
	}

}

package gmc.project.innovatree.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

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
	
	private String name;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String phoneNummber;

}

package gmc.project.innovatree.achievements.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "Address")
@Entity
@Data
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = -4301245473483531268L;
	
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

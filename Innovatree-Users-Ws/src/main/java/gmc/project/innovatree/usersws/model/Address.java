package gmc.project.innovatree.usersws.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Address implements Serializable {

	private static final long serialVersionUID = 2558287257603141359L;
	
	private String name;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String phoneNummber;

}

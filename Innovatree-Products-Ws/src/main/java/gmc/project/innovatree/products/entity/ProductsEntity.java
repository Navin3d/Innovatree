package gmc.project.innovatree.products.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import gmc.project.innovatree.products.models.Category;
import lombok.Data;

@Entity
@Data
@Table(name = "Products")
public class ProductsEntity implements Serializable {

	private static final long serialVersionUID = -1854303438880549438L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productId;
	
	private String name;
	
	private String imageUrl;

	private String description;
	
	private String price;	
	
	@Enumerated(value = EnumType.STRING)
	private Category category; 
	
	private int purchases;
	
	private boolean isTopSelling;

}

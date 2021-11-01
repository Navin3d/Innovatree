package gmc.project.innovatree.products.shared;

import java.io.Serializable;

import gmc.project.innovatree.products.models.Category;
import lombok.Data;

@Data
public class ProductsDto implements Serializable {

	private static final long serialVersionUID = -1907325907858023307L;
	
	private String productId;
	private String name;
	private String imageUrl;
	private String description;
	private String price;
	private Category category;

}

package gmc.project.innovatree.shop.shared;

import java.io.Serializable;

import gmc.project.innovatree.shop.models.Category;
import lombok.Data;

@Data
public class ProductsDto implements Serializable {

	private static final long serialVersionUID = 6349915270022974166L;
	
	private String productId;
	private String name;
	private String imageUrl;
	private String description;
	private Integer price;
	private Category category;

}

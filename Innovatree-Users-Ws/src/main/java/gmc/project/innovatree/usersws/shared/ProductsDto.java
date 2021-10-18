package gmc.project.innovatree.usersws.shared;

import java.io.Serializable;

import gmc.project.innovatree.usersws.model.Category;
import lombok.Data;

@Data
public class ProductsDto implements Serializable {

	private static final long serialVersionUID = -2972854633835533241L;
	
	private String productId;
	private String name;
	private String imageUrl;
	private String description;
	private String price;
	private Category category;
	private boolean isTopSelling;
	private int purchases;

}

package gmc.project.innovatree.shop.shared;

import java.io.Serializable;

import gmc.project.innovatree.shop.models.Category;
import lombok.Data;

@Data
public class ProductDto implements Serializable {

    private String productId;
    private String name;
    private String imageUrl;
    private String price;
    private String description;
    private Category category;
    private String isBestSelling;
    
}

package gmc.project.innovatree.product.products.models;

import lombok.Data;

@Data
public class ProductModel {

    private String productId;
    private String name;
    private String imageUrl;
    private String price;
    private Category category;
    private String description;
    private boolean isBestSelling;
    
}

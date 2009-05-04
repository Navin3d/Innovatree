package gmc.project.innovatree.products.shared;

import java.io.Serializable;

import lombok.Data;

@Data
class ProductDto implements Serializable {

    private String productId;
    private String name;
    private String imageUrl;
    private String price;
    private String description;
    private Category category;
    private String isBestSelling;    

}

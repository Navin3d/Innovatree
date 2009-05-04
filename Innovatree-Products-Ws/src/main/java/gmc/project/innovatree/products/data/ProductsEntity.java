package gmc.project.innovatree.product.data;

import java.io.Serializable;

@Table(name = "Products")
public class ProductsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Product_Id", nullable = false, unique = true)
    private String productId;
    private String name;
    private String imageUrl;
    private String price;
    private String description;

    @Ennumerated(EnnumerationType.STRING)
    private Category category;
    private boolean isBestSelling;
    
}

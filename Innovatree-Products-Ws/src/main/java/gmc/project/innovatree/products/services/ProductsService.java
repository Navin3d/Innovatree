package gmc.project.innovatree.products.services;

import java.util.List;

import gmc.project.innovatree.products.shared.ProductsDto;

public interface ProductsService {
	ProductsDto findOne(String productId);
	ProductsDto save(ProductsDto products);
	void deleteByProductId(String productId);
	List<ProductsDto> findAllProducts();
	List<ProductsDto> findTopSelling();
}

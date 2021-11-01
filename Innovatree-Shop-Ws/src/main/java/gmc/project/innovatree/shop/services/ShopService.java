package gmc.project.innovatree.shop.services;

import gmc.project.innovatree.shop.shared.ProductsDto;

public interface ShopService {
	public ProductsDto buyProduct(String userId, String productId);
}

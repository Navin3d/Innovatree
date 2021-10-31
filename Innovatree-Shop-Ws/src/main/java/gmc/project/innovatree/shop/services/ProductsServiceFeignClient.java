package gmc.project.innovatree.shop.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gmc.project.innovatree.shop.shared.ProductsDto;

@FeignClient("Innovatree-Product-Ws")
public interface ProductsServiceFeignClient {
	
	@GetMapping("/product/topselling")
	public List<ProductsDto> getTopSellingProduct();
	
	@GetMapping("/product")
	public List<ProductsDto> getAllProducts();
	
	@GetMapping("/product/{productId}/show")
	public ProductsDto findProductById(@PathVariable String productId);

}

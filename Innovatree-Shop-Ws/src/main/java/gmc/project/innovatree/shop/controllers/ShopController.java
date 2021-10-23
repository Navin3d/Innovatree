package gmc.project.innovatree.shop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.innovatree.shop.services.ProductsServiceFeignClient;
import gmc.project.innovatree.shop.services.ShopService;
import gmc.project.innovatree.shop.shared.ProductsDto;

@RestController
@RequestMapping("/shop")
public class ShopController {
	
	private final ShopService shopService;
	private final ProductsServiceFeignClient productsServiceFeignClient;

	public ShopController(ShopService shopService, ProductsServiceFeignClient productsServiceFeignClient) {
		super();
		this.shopService = shopService;
		this.productsServiceFeignClient = productsServiceFeignClient;
	}

	@GetMapping("/topSelling")
	public ResponseEntity<List<ProductsDto>> getTopSelling() {
		List<ProductsDto> returnValue = productsServiceFeignClient.getTopSellingProduct();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@GetMapping("/allproduct")
	public ResponseEntity<List<ProductsDto>> getAll() {
		List<ProductsDto> returnValue = productsServiceFeignClient.getAllProducts();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@GetMapping("/{productId}/buy/{userId}")	
	public String buyProduct(@PathVariable String userId, @PathVariable String productId) {
		shopService.buyProduct(userId, productId);
		return "redirect:/shop";
	}
	
}

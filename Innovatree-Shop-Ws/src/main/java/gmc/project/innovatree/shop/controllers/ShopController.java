package gmc.project.innovatree.shop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.innovatree.shop.models.ProductModel;
import gmc.project.innovatree.shop.services.ProductsService;
import gmc.project.innovatree.shop.services.ShopService;
import gmc.project.innovatree.shop.shared.ProductDto;

@RestController
@RequestMapping("/shop")
public class ShopController {

	private final ShopService shopService;
	private final ProductsService productsService;

	public ShopController(ShopService shopService, ProductsService productsService) {
		this.shopService = shopService;
		this.productsService = productsService;
	}

	@GetMapping
	public ResponseEntity<List<ProductDto>> shopProductsPage() {
		List<ProductDto> returnValue = productsService.getAllProduct();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
}

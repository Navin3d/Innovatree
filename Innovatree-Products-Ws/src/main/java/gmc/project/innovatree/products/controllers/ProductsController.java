package gmc.project.innovatree.products.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.innovatree.products.services.ProductsService;
import gmc.project.innovatree.products.shared.ProductsDto;

@RestController
@RequestMapping("/product")
public class ProductsController {
	
	private final ProductsService productsService;

	public ProductsController(ProductsService productsService) {
		super();
		this.productsService = productsService;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductsDto>> getAllProduct() {
		List<ProductsDto> returnValue = productsService.findAllProducts();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@GetMapping("/{productId}/show")
	public ResponseEntity<ProductsDto> getProductByProductId(@PathVariable String productId) {
		ProductsDto returnValue = productsService.findOne(productId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@GetMapping("/{productId}/edit")
	public ResponseEntity<ProductsDto> editProductByProductId(@PathVariable String productId) {
		ProductsDto returnValue = productsService.findOne(productId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@GetMapping("/{productId}/delete")
	public String deleteProductById(@PathVariable String productId) {
		productsService.deleteByProductId(productId);
		return "redirect:/product";
	}
	
	@PostMapping
	public String createOrUpdateProducts(@RequestBody ProductsDto product) {
		ProductsDto productSaved = productsService.save(product);
		return "redirect:/product/" + productSaved.getProductId() + "/show";
	}

}

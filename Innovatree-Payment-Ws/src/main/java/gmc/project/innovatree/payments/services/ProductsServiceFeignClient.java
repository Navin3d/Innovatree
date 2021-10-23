package gmc.project.innovatree.payments.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gmc.project.innovatree.payments.shared.ProductsDto;

@FeignClient("/Innovatree-Products-Ws")
public interface ProductsServiceFeignClient {
	
	@PostMapping("/product/{productId}/show")
	public ProductsDto getProductById(@PathVariable String productId);

}

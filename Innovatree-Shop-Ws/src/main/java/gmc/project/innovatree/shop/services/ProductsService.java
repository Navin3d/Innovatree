package gmc.project.innovatree.shop.services;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import gmc.project.innovatree.shop.shared.ProductDto;


@FeignClient(name = "Innovatree-Product-Ws")
public interface ProductsService {

    @GetMapping("/products")
    List<ProductDto> getAllProduct();
    
}

package gmc.project.innovatree.products.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.innovatree.products.entity.ProductsEntity;

public interface ProductsDao extends JpaRepository<ProductsEntity, Long> {
	ProductsEntity findByProductId(String productId);
}

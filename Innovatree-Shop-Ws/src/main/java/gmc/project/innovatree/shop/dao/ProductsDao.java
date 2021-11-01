package gmc.project.innovatree.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.innovatree.shop.entities.ProductsEntity;

public interface ProductsDao extends JpaRepository<ProductsEntity, Long> {
	ProductsEntity findByProductId(String productId);
}

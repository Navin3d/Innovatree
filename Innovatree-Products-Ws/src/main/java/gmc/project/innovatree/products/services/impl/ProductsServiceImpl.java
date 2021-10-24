package gmc.project.innovatree.products.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import gmc.project.innovatree.products.entity.ProductsEntity;
import gmc.project.innovatree.products.services.ProductsService;
import gmc.project.innovatree.products.dao.ProductsDao;
import gmc.project.innovatree.products.shared.ProductsDto;

@Service
public class ProductsServiceImpl implements ProductsService {
	
	private final ProductsDao productsDao;

	public ProductsServiceImpl(ProductsDao productsDao) {
		super();
		this.productsDao = productsDao;
	}

	@Override
	public ProductsDto findOne(String productId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ProductsEntity foundProduct = productsDao.findByProductId(productId);
		ProductsDto returnValue = modelMapper.map(foundProduct, ProductsDto.class);
		
		return returnValue;
	}

	@Override
	public ProductsDto save(ProductsDto products) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		ProductsEntity foundProduct = null;
		
		try {
			
			foundProduct = productsDao.findByProductId(products.getProductId());
			
		} catch (Exception e) {
			
			foundProduct.setProductId(UUID.randomUUID().toString());

		}
		
		foundProduct = modelMapper.map(products, ProductsEntity.class);
		
		ProductsEntity savedProduct = productsDao.save(foundProduct);
		
		ProductsDto returnValue = modelMapper.map(savedProduct, ProductsDto.class);
		
		return returnValue;
	}

	@Override
	public void deleteByProductId(String productId) {
		ProductsEntity foundProduct = productsDao.findByProductId(productId);
		productsDao.delete(foundProduct);
	}

	@Override
	public List<ProductsDto> findAllProducts() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		List<ProductsDto> returnValue = new ArrayList<>();
		productsDao.findAll().iterator().forEachRemaining((product) -> returnValue.add(modelMapper.map(product, ProductsDto.class)));
		
		return returnValue;
	}

	@Override
	public List<ProductsDto> findTopSelling() {		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		List<ProductsEntity> foundProducts = productsDao.findAll().stream()
						.filter(product -> product.isTopSelling()).toList();
		
		List<ProductsDto> returnValue = new ArrayList<>();
		
		foundProducts.iterator().forEachRemaining(product -> returnValue.add(modelMapper.map(product, ProductsDto.class)));
		
		return returnValue;
	}

}

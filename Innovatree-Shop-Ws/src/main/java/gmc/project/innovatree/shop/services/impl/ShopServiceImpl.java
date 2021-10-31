package gmc.project.innovatree.shop.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import gmc.project.innovatree.shop.InsufficientBalanceException;
import gmc.project.innovatree.shop.dao.ProductsDao;
import gmc.project.innovatree.shop.entities.ProductsEntity;
import gmc.project.innovatree.shop.models.BillPayingModel;
import gmc.project.innovatree.shop.services.PaymentServiceFeignClient;
import gmc.project.innovatree.shop.services.ShopService;
import gmc.project.innovatree.shop.shared.ProductsDto;
import gmc.project.innovatree.shop.shared.WalletDto;

@Service
public class ShopServiceImpl implements ShopService {
	
	private final ProductsDao productsDao;
	private final Environment environment;
	private final PaymentServiceFeignClient paymentServiceFeignClient;

	public ShopServiceImpl(ProductsDao productsDao,
			PaymentServiceFeignClient paymentServiceFeignClient, Environment environment) {
		super();
		this.productsDao = productsDao;
		this.environment = environment;
		this.paymentServiceFeignClient = paymentServiceFeignClient;
	}

	@Override
	public ProductsDto buyProduct(String userId, String productId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Integer topSellingLimit = Integer.valueOf(environment.getProperty("product.top-selling.limit"));
		
		ProductsEntity foundProduct = productsDao.findByProductId(productId);
		WalletDto foundUser = paymentServiceFeignClient.getUserBalance(userId);
		
		BillPayingModel billPayingModel = new BillPayingModel();
		billPayingModel.setTotalAmount(foundProduct.getPrice());
		billPayingModel.setUserId(foundUser.getUserId());
		billPayingModel.setPinNumber("9771");
		
		try {
			paymentServiceFeignClient.payBill(billPayingModel);
		} catch(InsufficientBalanceException e) {
			throw new InsufficientBalanceException(e);
		}
		
		foundProduct.setPurchases(foundProduct.getPurchases() + 1);
		
		if(foundProduct.getPurchases() > topSellingLimit)
			foundProduct.setTopSelling(true);
			
		productsDao.save(foundProduct);
		ProductsDto returnValue = modelMapper.map(foundProduct, ProductsDto.class);
		
		return returnValue;
	}

}

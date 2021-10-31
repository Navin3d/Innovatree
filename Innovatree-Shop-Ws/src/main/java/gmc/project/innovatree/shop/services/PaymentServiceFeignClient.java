package gmc.project.innovatree.shop.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import gmc.project.innovatree.shop.models.BillPayingModel;
import gmc.project.innovatree.shop.shared.WalletDto;

@FeignClient("Innovatree-Payment-Ws")
public interface PaymentServiceFeignClient {

	@GetMapping("/payment/{userId}/balance")
	public WalletDto getUserBalance(@PathVariable String userId);
	
	@PostMapping("/payment")
	public WalletDto payBill(@RequestBody BillPayingModel billPayingModel);
	
}

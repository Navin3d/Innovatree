package gmc.project.innovatree.payments.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.innovatree.payments.models.BillPayingModel;
import gmc.project.innovatree.payments.models.RechargeModel;
import gmc.project.innovatree.payments.services.PaymentsService;
import gmc.project.innovatree.payments.shared.WalletDto;

@RestController
@RequestMapping("/payment")
public class PaymentsController {
	
	private final PaymentsService paymentsService;

	public PaymentsController(PaymentsService paymentsService) {
		super();
		this.paymentsService = paymentsService;
	}
	
	@GetMapping("/{userId}/balance")
	public WalletDto getUserBalance(@PathVariable String userId) {
		WalletDto returnValue = paymentsService.findUserBalanceByUserId(userId);
		return returnValue;
	}
	
	@PostMapping
	public WalletDto payBill(@RequestBody BillPayingModel billPayingModel) {
		WalletDto returnValue = paymentsService.reduceAmountFromWallet(billPayingModel);
		return returnValue;
	}
	
	@PostMapping("/recharge")
	public WalletDto rechargeWallet(@RequestBody RechargeModel rechargeModel) {
		WalletDto returnValue = paymentsService.rechargeWallet(rechargeModel);
		return returnValue;
	}

}

package gmc.project.innovatree.payments.controllers;

import org.springframework.web.bind.annotation.RestController;

import gmc.project.innovatree.payments.services.PaymentsService;

@RestController
public class PaymentsController {
	
	private final PaymentsService paymentsService;

	public PaymentsController(PaymentsService paymentsService) {
		super();
		this.paymentsService = paymentsService;
	}
	
	

}

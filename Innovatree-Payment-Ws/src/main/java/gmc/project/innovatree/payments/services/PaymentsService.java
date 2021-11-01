package gmc.project.innovatree.payments.services;

import gmc.project.innovatree.payments.models.BillPayingModel;
import gmc.project.innovatree.payments.models.RechargeModel;
import gmc.project.innovatree.payments.shared.WalletDto;

public interface PaymentsService {
	WalletDto findUserBalanceByUserId(String userId);
	WalletDto rechargeWallet(RechargeModel rechargeModel);
	WalletDto reduceAmountFromWallet(BillPayingModel billPayingModel);
}

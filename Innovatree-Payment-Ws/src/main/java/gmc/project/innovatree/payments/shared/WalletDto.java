package gmc.project.innovatree.payments.shared;

import java.io.Serializable;

import lombok.Data;

@Data
public class WalletDto implements Serializable {

	private static final long serialVersionUID = -1581741495958509943L;
	
	private String userId;
	private Integer walletBalence;
	private Integer amountPaid;
	private Integer amountEarned;
	private boolean isEnable;

}

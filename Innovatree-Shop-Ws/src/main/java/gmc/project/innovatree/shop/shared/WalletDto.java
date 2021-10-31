package gmc.project.innovatree.shop.shared;

import java.io.Serializable;

import lombok.Data;

@Data
public class WalletDto implements Serializable {

	private static final long serialVersionUID = 6770472009757241303L;
	
	private String userId;
	private Integer walletBalence;
	private Integer amountPaid;
	private Integer amountEarned;
	private boolean isEnable;

}

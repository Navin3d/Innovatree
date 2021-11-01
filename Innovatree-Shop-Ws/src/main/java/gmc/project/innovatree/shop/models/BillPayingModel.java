package gmc.project.innovatree.shop.models;

import lombok.Data;

@Data
public class BillPayingModel {

	private String userId;
	private String pinNumber;
	private Integer totalAmount;
	
}

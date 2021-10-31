package gmc.project.innovatree.shop.models;

import lombok.Data;

@Data
public class RechargeModel {
	
	private String userId;
	private String pinNumber;
	private Integer rechargeAmount;

}
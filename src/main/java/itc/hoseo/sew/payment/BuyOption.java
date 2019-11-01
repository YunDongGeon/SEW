package itc.hoseo.sew.payment;

import lombok.Data;

@Data
public class BuyOption {
	private int optionNo;
	private int prodNo;
	private String orderNo;
	private String prodColor;
	private String prodSize;
	private int prodAmount;
}

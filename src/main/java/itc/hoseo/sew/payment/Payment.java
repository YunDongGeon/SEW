package itc.hoseo.sew.payment;

import java.util.List;

import lombok.Data;

@Data
public class Payment {
	private String memId;
	private int prodNo;	
	private String prodName;
	private int prodDeli;
	private String prodThumbName;
	private int totalListPrice;
	private int totalPrice;
	private int totalAmount;
	private List<BuyOption> optionList;
}

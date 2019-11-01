package itc.hoseo.sew.payment;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class Payment {
	private String orderNo;
	private String memId;
	private int prodNo;	
	private String prodName;
	private int prodDeli;
	private String prodThumbName;
	private int totalListPrice;
	private int totalPrice;
	private int totalAmount;
	private int totalDeli;
	private Timestamp orderDate;
	private List<BuyOption> optionList;
}

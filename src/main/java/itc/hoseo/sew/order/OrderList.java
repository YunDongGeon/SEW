package itc.hoseo.sew.order;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrderList {
	private String orderNo;
	private String orderProdNo;	
	private String memId;
	private int totalListCost;
	private int totalCost;
	private int totalDiscount;
	private int totalDeli;
	private int totalUsedPoint;
	private String receiverName;
	private String receiverContact;
	private String deliZipcode;
	private String deliAddr1;
	private String deliAddr2;
	private String payType;
	private String payCompany;
	private Timestamp orderDate;
}

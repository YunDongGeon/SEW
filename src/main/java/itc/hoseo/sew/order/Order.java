package itc.hoseo.sew.order;

import java.util.List;

import lombok.Data;

@Data
public class Order {
	private String orderNo;
	private String orderProdNo;
	private int prodNo;	
	private int prodCost;
	private int prodAmount;
	private String prodName;
	private String prodThumbName;
	private List<OrderOption> optionList;
}

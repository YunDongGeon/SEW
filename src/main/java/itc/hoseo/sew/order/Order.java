package itc.hoseo.sew.order;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class Order {
	private String orderProdNo;	
	private int prodNo;	
	private String prodName;
	private int prodCost;
	private int prodAmount;	
	private List<OrderOption> optionList;
}

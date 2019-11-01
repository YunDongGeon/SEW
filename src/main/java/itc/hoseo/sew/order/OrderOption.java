package itc.hoseo.sew.order;

import lombok.Data;

@Data
public class OrderOption {
	private String orderProdNo;
	private String orderColor;
	private String orderSize;
	private int orderAmount;
}

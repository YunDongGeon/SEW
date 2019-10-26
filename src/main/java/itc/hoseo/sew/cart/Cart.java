package itc.hoseo.sew.cart;

import java.util.List;

import lombok.Data;

@Data
public class Cart {
	private int cartNo;
	private String memId;
	private int prodNo;	
	private String prodName;
	private int prodDeli;
	private String prodThumbName;
	private String prodColor;
	private String prodSize;
	private int prodAmount;
	private int totalPrice;
	private int totalAmount;
	private List<CartOption> optionList;
}

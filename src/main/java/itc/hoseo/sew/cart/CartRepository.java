package itc.hoseo.sew.cart;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartRepository {
	public int addCart(Cart c);
	public int addOption(Cart c);
	public int delCartItem(Cart c);
	public List<Cart> getCart(String memId);
	public List<CartOption> getOption(String memId);
}
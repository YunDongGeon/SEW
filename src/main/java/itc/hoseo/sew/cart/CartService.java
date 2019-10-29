package itc.hoseo.sew.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	@Autowired
	CartRepository repo;
	
	public int addCart(Cart c) {
		return repo.addCart(c);
	}
	
	public boolean addOption(CartOption cp) {
		return repo.addOption(cp)!=0;
	}
	
	public Cart getSelectCart(Cart c) {
		return repo.getSelectCart(c);
	}
	
	public List<Cart> getCart(String memId) {
		return repo.getCart(memId);
	}
	
	public List<CartOption> getOption(String memId) {
		return repo.getOption(memId);
	}
	
	public int delCartItem(Cart c) {
		return repo.delCartItem(c);
	}	
}

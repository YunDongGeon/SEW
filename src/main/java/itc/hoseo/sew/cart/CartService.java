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
	
	public boolean addOption(Cart c) {
		return repo.addOption(c)!=0;
	}
	
	public List<Cart> getCart(String memId) {
		return repo.getCart(memId);
	}
	
	public List<CartOption> getOption(String memId) {
		return repo.getOption(memId);
	}
	
}

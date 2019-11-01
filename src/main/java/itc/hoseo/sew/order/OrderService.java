package itc.hoseo.sew.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	OrderRepository repo;
	
	public boolean addOrder(Order o) {
		return repo.addOrder(o)!=0;
	}
	
	public boolean addOrderOption(OrderOption op) {
		return repo.addOrderOption(op)!=0;
	}
	
	public boolean addOrderList(OrderList ol) {
		return repo.addOrderList(ol)!=0;
	}
}

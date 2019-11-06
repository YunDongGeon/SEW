package itc.hoseo.sew.order;

import java.util.List;

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
	
	public List<Order> getOrderProd(String orderNo) {
		return repo.getOrderProd(orderNo);
	}
	
	public List<OrderOption> getOrderOption(String orderProdNo) {
		return repo.getOrderOption(orderProdNo);
	}
	
	public boolean updateMemPoint(OrderPoint omp) {
		return repo.updateMemPoint(omp)!=0;
	}
	
	public OrderInven getInven(OrderOption op) {
		return repo.getInven(op);
	}
	
	public boolean updateInven(OrderInven oi) {
		return repo.updateInven(oi)!=0;
	}
}

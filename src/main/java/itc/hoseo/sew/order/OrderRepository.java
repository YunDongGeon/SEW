package itc.hoseo.sew.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import itc.hoseo.sew.cart.CartOption;

@Mapper
public interface OrderRepository {
	public int addOrder(Order o);
	public int addOrderOption(OrderOption op);
	public int addOrderList(OrderList ol);
	public List<Order> getOrderProd(String orderNo);
	public List<OrderOption> getOrderOption(String orderProdNo);
	public int updateMemPoint(OrderPoint omp);
}

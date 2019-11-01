package itc.hoseo.sew.order;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRepository {
	public int addOrder(Order o);
	public int addOrderOption(OrderOption op);
	public int addOrderList(OrderList ol);
}

package itc.hoseo.sew.payment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {
	public int addOrder(Payment p);
}

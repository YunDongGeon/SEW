package itc.hoseo.sew.mypage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.mypage.MyPage;
import itc.hoseo.sew.order.Order;
import itc.hoseo.sew.order.OrderList;

@Mapper
public interface MyPageRepository {
	public MyPage getMemPoint(MyPage mp);
	public Member getEditMemInfo(Member mem);
	public int editMemInfo(Member mem);	
	public int withDrawal(Member mem);
	public List<OrderList> getOrderList(Member mem);
	public List<OrderList> addOrderList(Member mem);
	public List<Order> getOrderProd(String s);
}

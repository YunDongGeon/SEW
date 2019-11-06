package itc.hoseo.sew.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;
import itc.hoseo.sew.order.Order;
import itc.hoseo.sew.order.OrderList;

@Service
public class MyPageService {
	@Autowired
	private MyPageRepository repo;
	@Autowired
	private MemberService service;
	
	public MyPage getMemPoint(MyPage mp) {
		return repo.getMemPoint(mp);
	}
	
	public Member getEditMemInfo(Member mem) {		
		return repo.getEditMemInfo(mem);
	}
	
	public boolean editMemInfo(Member mem) {
		service.encryp(mem);
		return repo.editMemInfo(mem)!=0;
	}
	
	public boolean withDrawal(Member mem) {
		return repo.withDrawal(mem)!=0;
	}
	
	public List<OrderList> getOrderList(Member mem) {
		return repo.getOrderList(mem);
	}
	
	public List<Order> getOrderProd(String s) {
		return repo.getOrderProd(s);
	}
}
 
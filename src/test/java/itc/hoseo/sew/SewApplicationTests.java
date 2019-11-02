package itc.hoseo.sew;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import itc.hoseo.sew.find.FindService;
import itc.hoseo.sew.management.Management;
import itc.hoseo.sew.management.ManagementService;
import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;
import itc.hoseo.sew.order.OrderOption;
import itc.hoseo.sew.order.Order;
import itc.hoseo.sew.order.OrderList;
import itc.hoseo.sew.order.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SewApplicationTests {
	@Autowired
	OrderService service;
	@Test
	public void addOrder() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Order o = new Order();
		OrderOption op = new OrderOption();
		OrderList ol = new OrderList();
		o.setProdNo(1);
		o.setProdAmount(2);
		o.setProdCost(29900);
		service.addOrder(o);
		op.setOrderProdNo(o.getOrderProdNo());
		op.setOrderColor("화이트");
		op.setOrderSize("M 사이즈");
		op.setOrderAmount(1);
		service.addOrderOption(op);
		ol.setTotalListCost(69900);
		ol.setTotalDiscount(40000);
		ol.setTotalDeli(2500);
		ol.setTotalUsedPoint(0);
		ol.setTotalCost(29900);
		ol.setMemId("test");
		ol.setReceiverContact("테스터");
		ol.setReceiverContact("01012345678");
		ol.setDeliZipcode("07583");
		ol.setDeliAddr1("서울특별시 강서구 강서로 420");
		ol.setDeliAddr2("서울호서전문학교 1호관 705호");
		ol.setPayType("신용카드");
		ol.setOrderDate(ts);
		service.addOrderList(ol);
	}
	
//	@Autowired
//	FindService service;
//	@Test
//	public void sendEmail() {
//		Member mem = new Member();
//		mem.setMemEmail("dbsehdrjs20@gmail.com");
//		mem.setMemId("test");
//		
//		service.sendEmail(mem);
//	}
//	@Autowired
//	MemberService memService;
//	@Test
//	public void encryption() {
//		Member mem = new Member();
//		
//		mem.setMemPw("test");
//		
//		mem = memService.encryp(mem);
//		
//		System.out.println(mem.getMemPw());
//	}

}

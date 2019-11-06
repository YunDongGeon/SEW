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
import itc.hoseo.sew.order.OrderInven;
import itc.hoseo.sew.order.OrderList;
import itc.hoseo.sew.order.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SewApplicationTests {
	@Autowired
	OrderService service;
	@Test
	public void test() {
		OrderOption op = new OrderOption();
		OrderInven oi = new OrderInven();
		op.setProdNo(1);
		op.setOrderColor("화이트");
		op.setOrderSize("S 사이즈");
		op.setOrderAmount(1);
		oi = service.getInven(op);
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

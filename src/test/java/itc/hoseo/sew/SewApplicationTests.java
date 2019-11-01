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
import itc.hoseo.sew.payment.Payment;
import itc.hoseo.sew.payment.PaymentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SewApplicationTests {
	@Autowired
	PaymentService service;
	@Test
	public void addOrder() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Payment p = new Payment();
		p.setProdNo(1);
		p.setTotalAmount(2);
		p.setTotalPrice(29900);
		p.setTotalDeli(2500);
		p.setOrderDate(ts);
		service.addOrder(p);
		
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

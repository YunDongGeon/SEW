package itc.hoseo.sew;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class SewApplicationTests {
	@Autowired
	ManagementService service;
	@Test
	public void addMenProd() {
		Management manage = new Management();
		manage.setProdType("top");
		manage.setProdCat("맨투맨/후드");
		manage.setProdName("남성 분또 가로블럭 맨투맨");
		manage.setProdListP(59900);
		manage.setProdPrice(24900);
		manage.setProdDeli(2500);
		manage.setProdCode("KA9S1-MKL010");
		manage.setProdOrigin("기타국가");
		manage.setProdContName("내용");
		
		service.addProd(manage);
		
		
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

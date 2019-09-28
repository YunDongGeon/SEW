package itc.hoseo.sew;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import itc.hoseo.sew.find.FindService;
import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SewApplicationTests {
	@Autowired
	FindService service;
	@Test
	public void sendEmail() {
		Member mem = new Member();
		mem.setMemEmail("dbsehdrjs20@gmail.com");
		mem.setMemId("admin");
		
		service.sendEmail(mem);
	}
//	public void encryption() {
//		Member mem = new Member();
//		
//		mem.setMemPw("tmdwls12");
//		
//		mem = memService.encryp(mem);
//		
//		System.out.println(mem.getMemPw());
//	}

}

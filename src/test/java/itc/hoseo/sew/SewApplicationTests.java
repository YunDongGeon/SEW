package itc.hoseo.sew;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SewApplicationTests {
	@Autowired
	MemberService memService;
	@Test
	public void encryption() {
		Member mem = new Member();
		
		mem.setMemPw("admin");
		
		mem = memService.encryp(mem);
		
		System.out.println(mem.getMemPw());
	}

}

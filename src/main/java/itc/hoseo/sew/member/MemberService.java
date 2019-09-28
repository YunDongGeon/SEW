package itc.hoseo.sew.member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	Logger log = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	private MemberRepository memberRepositoty;
	
	public Member encryp(Member member) {
		MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
		}catch(NoSuchAlgorithmException nae) {
			log.error("암호화 알고리즘 초기화 에러",nae);
			throw new RuntimeException(nae);
		}
		
		digest.update(member.getMemPw().getBytes());
		StringBuilder sb = new StringBuilder();
		for(byte b: digest.digest()) {
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}		
		member.setMemPw(sb.toString()); 
		
		return member; 
	}
	
	public boolean addMember(Member member){
		member = encryp(member);	
		return memberRepositoty.addMember(member)==1;
	}
	public int memIdChk(String memId) {					
		return memberRepositoty.memIdChk(memId);
	}
	public boolean loginChk(Member member) {
		return memberRepositoty.loginChk(member)!=null;
	}
	public Member getMember(Member member) {					
		return memberRepositoty.getMember(member);
	}
	public Member findMemId(Member member) {
		return memberRepositoty.findMemId(member);
	}
	public Member findMemPw(Member member) {
		return memberRepositoty.findMemPw(member);
	}
	public boolean updateTempPw(Member member) {
		return memberRepositoty.updateTempPw(member)!=0;
	}
}

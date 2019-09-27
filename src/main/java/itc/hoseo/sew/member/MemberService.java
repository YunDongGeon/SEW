package itc.hoseo.sew.member;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberRepository;

@Service
public class MemberService {
	Logger log = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	private MemberRepository memberRepositoty;
	
	public boolean addMember(Member member){
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
	public int getMemberCount() {
		return memberRepositoty.getMemberCount();
	}
}

package itc.hoseo.sew.member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
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
	
	public void sendEmail(Member member) {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "springsew2019@gmail.com";
		String hostSMTPpwd = "sew2019spring";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "springsew2019@gmail.com";
		String fromName = "Spring Homepage";
		String subject = "";
		String msg = "";
		
		subject = "Spring Homepage 임시 비밀번호 입니다.";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += member.getMemId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
		msg += "<p>임시 비밀번호 : ";
		msg += member.getMemPw() + "</p></div>";
		
		String mail = member.getMemEmail().replace(" ", "");
		try {
			/*
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);
			email.setSslSmtpPort("465");
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
			*/
			
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setDebug(true);
			email.setAuthenticator(new DefaultAuthenticator(hostSMTPid, hostSMTPpwd));
			email.setTLS(true);
			email.setFrom(fromEmail);
			email.setSubject("TestMail");
			email.setMsg("This is a test mail ... :-)");
			email.addTo(mail);
			email.send();
			
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
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
}

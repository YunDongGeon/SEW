package itc.hoseo.sew.find;

import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;

@Service
public class FindService {
	Logger log = LoggerFactory.getLogger(MemberService.class);
	
	public static String randomPw(){
		char pwCollection[] = new char[] {
	                        '1','2','3','4','5','6','7','8','9','0',
	                        'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
	                        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
	                        '!','@','#','$','%','^','&','*','(',')'};//배열에 선언

	    String ranPw = "";

		for (int i = 0; i < 10; i++) {
			int selectRandomPw = (int)(Math.random()*(pwCollection.length));//Math.rondom()은 0.0이상 1.0미만의 난수를 생성해 준다.
			ranPw += pwCollection[selectRandomPw];
		}
	    return ranPw;
	}

	
	public void sendEmail(Member member) {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "springsew2019@gmail.com";
		String hostSMTPpwd = "sew2019spring";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "springsew2019@gmail.com";
		String fromName = "Sew";
		String subject = "";
		String msg = "";
		
		subject = "Sew 임시 비밀번호 입니다.";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += member.getMemId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
		msg += "<p>임시 비밀번호 : ";
		msg += member.getMemPw() + "</p></div>";
		
		String mail = member.getMemEmail().replace(" ", "");
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);
			email.setSslSmtpPort("465");
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
					
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}
}

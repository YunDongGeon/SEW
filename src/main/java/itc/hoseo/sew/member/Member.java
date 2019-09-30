package itc.hoseo.sew.member;

import lombok.Data;

@Data
public class Member {
	private String memId;
	private String memPw;
	private String memName;
	private String memBirth;
	private String memEmail;	
	private String memPhone;	
	private String memZipCode;
	private String memAddr1;
	private String memAddr2;
	private String memStat;
	private String memAuth;
	private String subject;
	private String content;
	private String receiver;
}

package itc.hoseo.sew.mypage;

import lombok.Data;

@Data
public class MyPage {
	private String memId;
	private String memPw;
	private String memName;
	private String memEmail;	
	private String memPhone;	
	private String memZipCode;
	private String memAddr1;
	private String memAddr2;
	private int memPoint;
}

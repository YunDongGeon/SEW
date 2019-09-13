package itc.hoseo.sew.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@PostMapping("/joinInput")
	public String termsAgree() {
		return "sewJoinInput";
	}
}

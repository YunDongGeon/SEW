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
	
	@PostMapping("/sewJoinDone")
	public String inputDong(Member m) {
		if (service.addMember(m)) {
			return "redirect:/";
		}
		return "redirect:/joinInput";
	}
}

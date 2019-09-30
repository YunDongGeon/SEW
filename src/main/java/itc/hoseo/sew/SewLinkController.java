package itc.hoseo.sew;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import itc.hoseo.sew.member.Member;

@Controller
public class SewLinkController {	
	@GetMapping("/index.do")
	public String index() {
		return "index";
	}
	@GetMapping("/login.do")
	public String getGoLogin() {
		return "sewLogin/sewLogin";
	}
	@PostMapping("/login.do")
	public String postGoLogin() {
		return "sewLogin/sewLogin";
	}
	@GetMapping("/logOut.do")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/index.do";
	}
	@GetMapping("/joinTerms.do")
	public String joinTerms() {
		return "sewJoin/sewJoinTerms";
	}
	@GetMapping("/myPage.do")
	public String myPage() {
		return "sewMyPage";		
	}
}

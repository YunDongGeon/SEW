package itc.hoseo.sew;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import itc.hoseo.sew.member.Member;

@Controller
public class SewLinkController {	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/login")
	public String getGoLogin() {
		return "sewLogin/sewLogin";
	}
	@PostMapping("/login")
	public String postGoLogin() {
		return "sewLogin/sewLogin";
	}
	@GetMapping("/logOut")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/joinTerms")
	public String joinTerms() {
		return "sewJoin/sewJoinTerms";
	}
	@GetMapping("/myPage")
	public String myPage() {
		return "sewMyPage";		
	}
}

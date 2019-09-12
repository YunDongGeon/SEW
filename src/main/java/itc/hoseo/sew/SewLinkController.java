package itc.hoseo.sew;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SewLinkController {	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		return "sewLogin";
	}
	@GetMapping("/joinTerms")
	public String joinTerms() {
		return "sewJoinTerms";
	}
}

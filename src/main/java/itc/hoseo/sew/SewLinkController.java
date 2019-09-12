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
	@PostMapping("/index")
	public String postIndex() {
		return "redirect:/index";
	}
	@GetMapping("/login")
	public String login() {
		return "sewLogin";
	}
	@GetMapping("/join")
	public String join() {
		return "sewJoinTerms";
	}
}

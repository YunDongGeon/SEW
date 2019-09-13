package itc.hoseo.sew.join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {
	@PostMapping("/joinInput")
	public String termsAgree() {
		return "sewJoinInput";
	}
}

package itc.hoseo.sew;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import itc.hoseo.sew.management.ManagementService;
import itc.hoseo.sew.member.Member;

@Controller
public class SewLinkController {	
	@Autowired
	ManagementService service;
	
	@GetMapping("/")
	public String main(ModelMap m) {
		return "redirect:index";
	}
	
	@GetMapping("/index")
	public String index(ModelMap m) {
		m.put("newProdList", service.getNewProd());
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
		return "redirect:/index";
	}
	@GetMapping("/joinTerms")
	public String joinTerms() {
		return "sewJoin/sewJoinTerms";
	}	
	@GetMapping("/withDrawal.do")
	public String withDrawal() {
		return "sewMyPage/sewWithDrawal";		
	}
	@GetMapping("/sewPayCheckout.do")
	public String sewCheckout() {
		return "sewProduct/sewCartOrderCheckout";		
	}
}

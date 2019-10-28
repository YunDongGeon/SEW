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
	
	@GetMapping("/index.do")
	public String index(ModelMap m) {
		m.put("newProdList", service.getNewProd());
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
		return "sewMyPage/sewMyPageHome";		
	}
	@GetMapping("/editMember.do")
	public String editMember() {
		return "sewMyPage/sewEditMember";		
	}
	@GetMapping("/withDrawal.do")
	public String withDrawal() {
		return "sewMyPage/sewWithDrawal";		
	}
	@GetMapping("/sewPayment.do")
	public String sewPayment() {
		return "sewProduct/sewPaymentPage";		
	}
}

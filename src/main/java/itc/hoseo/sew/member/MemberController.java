package itc.hoseo.sew.member;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi.SHA3_256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@PostMapping("/sewJoinInput")
	public String termsAgree(@ModelAttribute("member") Member member) {
		return "sewJoinInput";
	}
	
	@GetMapping("/sewJoinFinPage")
	public String joinFin(Model model) {
		model.addAttribute("member", new Member());		
		return "sewJoinFinPage";
	}
	
	@PostMapping("/sewJoinFinPage")
	public String joinFin(@ModelAttribute("member") Member member) {		
		if (service.addMember(member)) {
			return "sewJoinFin";
		} 
		return "sewJoinInput";		
	}	
	
	@PostMapping("/memInputIdChk")
	@ResponseBody
    public Map<Object, Object> memIdChk(@RequestBody String memId) {        
        int count = 0;
        Map<Object, Object> map = new HashMap<Object, Object>();
        count = service.memIdChk(memId);
        map.put("cnt", count);
        return map;
    }	
	
	@PostMapping("/joinFin")
	public String sewGoLogin() {
		return "sewLogin";
	}
	
	@PostMapping("/validIdPw")
	@ResponseBody
    public Map<Object, Object> vaildIdPw(@RequestBody Member member) {
		boolean isValid = false;
        Map<Object, Object> map = new HashMap<Object, Object>();
        member = service.encryp(member);
        if(service.loginChk(member)) {
        	isValid = true;
        	map.put("valid", isValid);
        } else {
        	map.put("valid", isValid);
        }
        return map;
    }
	
	@PostMapping("/sewLogin")
	public String loginChk(Member mem, HttpSession session) {
		mem = service.encryp(mem);
		if(service.getMember(mem)!=null) {
			mem = service.getMember(mem);
			session.setAttribute("mem", mem);			
			return "redirect:/";
		}
		return "/";
	}
	
	@GetMapping("/logOut")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

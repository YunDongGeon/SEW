package itc.hoseo.sew.member;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

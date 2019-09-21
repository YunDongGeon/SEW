package itc.hoseo.sew.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping("/memInputIdChk")
	@ResponseBody
    public Map<Object, Object> memIdChk(@RequestBody String memId) {        
        int count = 0;
        Map<Object, Object> map = new HashMap<Object, Object>();
        count = service.memIdChk(memId);
        map.put("cnt", count);
 
        return map;
    }	
}

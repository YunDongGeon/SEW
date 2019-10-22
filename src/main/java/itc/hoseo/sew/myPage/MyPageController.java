package itc.hoseo.sew.myPage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import itc.hoseo.sew.member.Member;


@Controller
public class MyPageController {
	@Autowired
	MyPageService service;
	
	@PostMapping("/getMemPoint.do")
	@ResponseBody
	public Map<Object, Object> getMemPoint(@RequestBody Member member) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		 
		return map;
	}
}

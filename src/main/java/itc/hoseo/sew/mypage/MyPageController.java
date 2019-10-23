package itc.hoseo.sew.mypage;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

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
	public Map<Object, Object> getMemPoint(MyPage mp, HttpSession session) {		
		Member mem = (Member)session.getAttribute("mem");
		mp.setMemId(mem.getMemId());
		Map<Object, Object> map = new HashMap<Object, Object>();
		mp = service.getMemPoint(mp);
		map.put("memPoint", mp.getMemPoint());
		return map;
	}
}

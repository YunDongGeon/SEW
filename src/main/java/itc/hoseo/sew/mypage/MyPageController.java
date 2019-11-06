package itc.hoseo.sew.mypage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;
import itc.hoseo.sew.order.Order;
import itc.hoseo.sew.order.OrderList;


@Controller
public class MyPageController {
	@Autowired
	MyPageService service;
	@Autowired
	MemberService mService;
	
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
	
	@GetMapping("/myPage.do")
	public String myPage(ModelMap m, Member mem, HttpSession session) {
		List<OrderList> orderList = new ArrayList<OrderList>();
		mem = (Member)session.getAttribute("mem");
		if(service.getOrderList(mem).isEmpty()) {
			m.put("orderList", 0);
		} else {
			orderList = service.getOrderList(mem);
			m.put("orderList", orderList);
		}
		return "sewMyPage/sewMyPageHome";		
	}
	
	@GetMapping("/editMember.do")
	public String editMemberPage(ModelMap m, HttpSession session, Member mem) {
		mem = (Member)session.getAttribute("mem");
		m.put("memInfo", service.getEditMemInfo(mem));
		return "sewMyPage/sewEditMember";		
	}
	@PostMapping("/sewEditMem.do")
	public String editMemberInfo(Member mem) {
		service.editMemInfo(mem);
		return "redirect:/myPage.do";
	}
	
	@PostMapping("/validMem.do")
	@ResponseBody
    public Map<Object, Object> vaildMem(@RequestBody Member member, HttpSession session) {
		boolean isValid = false;
		Member mem = (Member)session.getAttribute("mem");
		member.setMemId(mem.getMemId());
        Map<Object, Object> map = new HashMap<Object, Object>();        
        member = mService.encryp(member);
        if(mService.loginChk(member)) {
        	isValid = true;
        	map.put("valid", isValid);
        } else {
        	map.put("valid", isValid);
        }
        return map;
    }
	
	@PostMapping("/withDrawal.do")
	public String doWithDrawal(Member member, HttpSession session) {
		Member mem = (Member)session.getAttribute("mem");
		service.withDrawal(mem);
		session.invalidate();	
		return "index";
	}
}

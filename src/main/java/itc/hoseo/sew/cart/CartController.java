package itc.hoseo.sew.cart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import itc.hoseo.sew.management.Management;
import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;

@Controller
public class CartController {
	@Autowired
	CartService service;
	@Autowired
	MemberService mService;
	
	@PostMapping("/addCart.do")	
	public String addCart(Cart c, CartOption cp, HttpServletRequest r, HttpSession session) {
		String [] prodColor = r.getParameterValues("prodColor");
		String [] prodSize = r.getParameterValues("prodSize");
		String [] amount = r.getParameterValues("prodAmount");
		int [] prodAmount =Arrays.stream(amount).mapToInt(Integer::parseInt).toArray();
		Member mem = (Member)session.getAttribute("mem");
		String memId = mem.getMemId();		
		c.setMemId(memId);
		service.addCart(c);
		cp.setCartNo(c.getCartNo());
		for(int i = 0; i < prodColor.length; i++) {			
			cp.setProdColor(prodColor[i]);
			cp.setProdSize(prodSize[i]);
			cp.setProdAmount(prodAmount[i]);
			service.addOption(cp);
		}
		return "redirect:/myCart.do";
	}
	
	@GetMapping("/myCart.do")
	public String getCart(HttpSession session, ModelMap m, Cart c) {
		Member mem = (Member)session.getAttribute("mem");
		String memId = mem.getMemId();		
		if(service.getCart(memId).isEmpty()) {
			m.put("cartList", 0);
		} else {
			m.put("cartList", service.getCart(memId));
		}
		return "sewMyPage/sewMyPageCart";
	}
	
	@PostMapping("/delCart.do")	
	public String delCartItem(Cart c) {
		service.delCartItem(c);
		return "redirect:/myCart.do";
	}	
	
	@PostMapping("/delSelected.do")	
	public String delSelectedItem(HttpServletRequest r, Cart c) {
		String [] no = r.getParameterValues("cartNo"); 
		int [] cartNo =Arrays.stream(no).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < cartNo.length; i++) {
			c.setCartNo(cartNo[i]);
			service.delCartItem(c);
		}		
		return "redirect:/myCart.do";
	}
	
	@PostMapping("/sewCartPayment.do")
	public String goCartPayment(HttpServletRequest r, HttpSession session, ModelMap m, Cart c) {
		List<Cart> buyList = new ArrayList<Cart>();
		Member mem = (Member)session.getAttribute("mem");
		String memId = mem.getMemId();		
		c.setMemId(memId);
		String [] no = r.getParameterValues("cartNo");
		int [] cartNo =Arrays.stream(no).mapToInt(Integer::parseInt).toArray();
		
		for(int i = 0; i < cartNo.length; i++) {
			c.setCartNo(cartNo[i]);
			buyList.add(service.getSelectCart(c));
		}
		
		mem = mService.getMemInfo(mem);
		if(mem.getMemPhone().length()==11) {
			mem.setFirstNum(mem.getMemPhone().substring(0, 3));
			mem.setMiddleNum(mem.getMemPhone().substring(3, 7));
			mem.setEndNum(mem.getMemPhone().substring(7, 11));
		}else {
			mem.setFirstNum(mem.getMemPhone().substring(0, 3));
			mem.setMiddleNum(mem.getMemPhone().substring(3, 6));
			mem.setEndNum(mem.getMemPhone().substring(6, 10));
		}
		m.put("member", mem);
		m.put("selectList", buyList);
		
		return "sewProduct/sewCartPaymentPage";
	}
	
}

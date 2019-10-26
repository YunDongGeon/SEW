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

@Controller
public class CartController {
	@Autowired
	CartService service;
	
	@PostMapping("/addCart.do")	
	public String addCart(Cart c, HttpServletRequest r, HttpSession session) {
		String [] prodColor = r.getParameterValues("prodColor");
		String [] prodSize = r.getParameterValues("prodSize");
		String [] amount = r.getParameterValues("prodAmount");
		int [] prodAmount =Arrays.stream(amount).mapToInt(Integer::parseInt).toArray();
		Member mem = (Member)session.getAttribute("mem");
		String memId = mem.getMemId();		
		c.setMemId(memId);
		service.addCart(c);
		for(int i = 0; i < prodColor.length; i++) {
			c.setProdColor(prodColor[i]);
			c.setProdSize(prodSize[i]);
			c.setProdAmount(prodAmount[i]);
			service.addOption(c);
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
	
	@GetMapping("/delCart.do")
	@ResponseBody
	public Map<Object, Object> delCartItem(@RequestParam(value="cartNo") int cartNo, Cart c) {
		c.setCartNo(cartNo);
		service.delCartItem(c);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("data", "success");
		return map;
	}
}

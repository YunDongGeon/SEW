package itc.hoseo.sew.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

import itc.hoseo.sew.cart.Cart;
import itc.hoseo.sew.cart.CartOption;
import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;

@Controller
public class PaymentController {
	@Autowired
	PaymentService service;
	@Autowired
	MemberService mService;
	
	@PostMapping("/sewDirectPayment.do")	
	public String goDIrectPay(ModelMap m, Cart c, HttpServletRequest r, HttpSession session) {
		List<CartOption> optionList = new ArrayList<CartOption>();
		String [] prodColor = r.getParameterValues("prodColor");
		String [] prodSize = r.getParameterValues("prodSize");
		String [] prodAmount = r.getParameterValues("prodAmount");
		CartOption co = new CartOption();
		Member mem = (Member)session.getAttribute("mem");
		String memId = mem.getMemId();		
		c.setMemId(memId);			
		c.setProdNo(c.getProdNo());				
		for(int i = 0; i < prodColor.length; i++) {
			co = new CartOption();
			co.setProdColor(prodColor[i]);
			co.setProdSize(prodSize[i]);
			co.setProdAmount(Integer.parseInt(prodAmount[i]));
			optionList.add(co);			
		}	
		c.setOptionList(optionList);
		m.put("selectList", c);
		
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
		
		return "sewProduct/sewDirectPaymentPage";
	}
}

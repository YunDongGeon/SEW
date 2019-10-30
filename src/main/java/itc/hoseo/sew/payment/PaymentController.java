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

@Controller
public class PaymentController {
	@Autowired
	PaymentService service;
	
	@PostMapping("/sewDirectPayment.do")	
	public String goDIrectPay(ModelMap m, Payment p, HttpServletRequest r, HttpSession session) {
		List<BuyOption> optionList = new ArrayList<BuyOption>();
		String [] prodColor = r.getParameterValues("prodColor");
		String [] prodSize = r.getParameterValues("prodSize");
		String [] prodAmount = r.getParameterValues("prodAmount");
		BuyOption bo = new BuyOption();
		Member mem = (Member)session.getAttribute("mem");
		String memId = mem.getMemId();		
		p.setMemId(memId);			
		bo.setProdNo(p.getProdNo());				
		for(int i = 0; i < prodColor.length; i++) {
			bo = new BuyOption();
			bo.setProdColor(prodColor[i]);
			bo.setProdSize(prodSize[i]);
			bo.setProdAmount(Integer.parseInt(prodAmount[i]));
			optionList.add(bo);			
		}	
		p.setOptionList(optionList);
		m.put("selectList", p);
		return "sewProduct/sewDirectPaymentPage";
	}
}

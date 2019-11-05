package itc.hoseo.sew.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import itc.hoseo.sew.cart.Cart;
import itc.hoseo.sew.cart.CartOption;
import itc.hoseo.sew.cart.CartService;
import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;

@Controller
public class OrderController {
	@Autowired
	OrderService service;
	@Autowired
	MemberService mService;
	@Autowired
	CartService cService;
	
	@PostMapping("/sewDirectOrder.do")	
	public String goDirectOrder(ModelMap m, Cart c, HttpServletRequest r, HttpSession session) {
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
		
		return "sewProduct/sewDirectOrderPage";
	}
	
	@PostMapping("/sewOrder.do")	
	public String order(Cart c, Order o, OrderOption op, OrderList ol, HttpServletRequest r, HttpSession session, ModelMap m) {
		List<Cart> buyList = new ArrayList<Cart>();
		Member mem = (Member)session.getAttribute("mem");
		String memId = mem.getMemId();	
		c.setMemId(memId);
		String [] cartNo = r.getParameterValues("cartNo");
		
		for(int i = 0; i < cartNo.length; i++) {
			c.setCartNo(Integer.parseInt(cartNo[i]));
			buyList.add(cService.getSelectCart(c));
		}					
		Timestamp ts = new Timestamp(System.currentTimeMillis());		
		ol.setMemId(memId);
		ol.setReceiverContact(ol.getDeliTelNo1()+ol.getDeliTelNo2()+ol.getDeliTelNo3());		
		ol.setOrderDate(ts);
		service.addOrderList(ol);
		o.setOrderNo(ol.getOrderNo());		
		for(int i = 0; i < buyList.size(); i++) {			
			o.setProdNo(buyList.get(i).getProdNo());
			o.setProdCost(buyList.get(i).getTotalPrice());
			o.setProdAmount(buyList.get(i).getTotalAmount());
			List<CartOption> optionList = buyList.get(i).getOptionList();
			service.addOrder(o);						
			for(int j = 0; j < optionList.size(); j++) {
				op = new OrderOption();
				op.setOrderProdNo(o.getOrderProdNo());
				op.setOrderColor(optionList.get(j).getProdColor());
				op.setOrderSize(optionList.get(j).getProdSize());
				op.setOrderAmount(optionList.get(j).getProdAmount());
				service.addOrderOption(op);				
			}
		}
		m.put("orderList", ol);
//		m.put("orderProdList", service.getOrderProd(ol.getOrderNo()));
		return "sewProduct/sewCartOrderCheckout";
	}
}

package itc.hoseo.sew.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import itc.hoseo.sew.cart.Cart;
import itc.hoseo.sew.cart.CartOption;
import itc.hoseo.sew.cart.CartService;
import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberService;
import itc.hoseo.sew.mypage.MyPage;
import itc.hoseo.sew.mypage.MyPageService;

@Controller
public class OrderController {
	@Autowired
	OrderService service;
	@Autowired
	MemberService mService;
	@Autowired
	CartService cService;
	@Autowired
	MyPageService mpService;
	
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
	@PostMapping("/sewCartOrderCheck.do")
	@Transactional
	public String cartOrder(Cart c, Order o, OrderOption op, OrderList ol, OrderInven oi, HttpServletRequest r, 
				HttpSession session, HttpServletResponse response, ModelMap m) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			List<Cart> buyList = new ArrayList<Cart>();
			Member mem = (Member)session.getAttribute("mem");
			String memId = mem.getMemId();
			
			MyPage mp = new MyPage();		
			mp.setMemId(memId);
			int accPoint = Integer.parseInt(r.getParameter("memAccPoint"));
			mp = mpService.getMemPoint(mp);
			int memPoint = mp.getMemPoint();		
			int sumPoint = memPoint-ol.getTotalUsedPoint()+accPoint;		
			OrderPoint omp = new OrderPoint();
			omp.setMemId(memId);
			omp.setMemPoint(sumPoint);		
			service.updateMemPoint(omp);
			
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
					op.setProdNo(o.getProdNo());
					op.setOrderProdNo(o.getOrderProdNo());
					op.setOrderColor(optionList.get(j).getProdColor());
					op.setOrderSize(optionList.get(j).getProdSize());
					op.setOrderAmount(optionList.get(j).getProdAmount());
					oi = service.getInven(op);
					if(op.getOrderSize().equals("S 사이즈")) {
						int prodInven = oi.getProdSsize();
						if(prodInven == 0 || prodInven < op.getOrderAmount()) {
							out.println("<script>"
											+ "alert('품절된 상품입니다.'); "
											+ "location.href='myCart.do';"
									  + "</script>");
							out.flush();
							throw new Exception();							
						} else {
							prodInven = prodInven - op.getOrderAmount();
							oi.setProdSize("S 사이즈");
							oi.setProdSsize(prodInven);
							service.updateInven(oi);
						}					
					} else if(op.getOrderSize().equals("M 사이즈")) {
						int prodInven = oi.getProdMsize();
						if(prodInven == 0 || prodInven < op.getOrderAmount()) {
							out.println("<script>"
									+ "alert('품절된 상품입니다.'); "
									+ "location.href='myCart.do';"
							  + "</script>");
							out.flush();
							throw new Exception();
						} else {
							prodInven = prodInven - op.getOrderAmount();
							oi.setProdSize("M 사이즈");
							oi.setProdMsize(prodInven);
							service.updateInven(oi);
						}
					} else if(op.getOrderSize().equals("L 사이즈")) {
						int prodInven = oi.getProdLsize();
						if(prodInven == 0 || prodInven < op.getOrderAmount()) {
							out.println("<script>"
									+ "alert('품절된 상품입니다.'); "
									+ "location.href='myCart.do';"
							  + "</script>");
							out.flush();
							throw new Exception();
						} else {
							prodInven = prodInven - op.getOrderAmount();
							oi.setProdSize("L 사이즈");
							oi.setProdLsize(prodInven);
							service.updateInven(oi);
						}
					} else if(op.getOrderSize().equals("XL 사이즈")) {
						int prodInven = oi.getProdXLsize();
						if(prodInven == 0 || prodInven < op.getOrderAmount()) {
							out.println("<script>"
									+ "alert('품절된 상품입니다.'); "
									+ "location.href='myCart.do';"
							  + "</script>");
							out.flush();
							throw new Exception();
						} else {
							prodInven = prodInven - op.getOrderAmount();
							oi.setProdSize("XL 사이즈");
							oi.setProdXLsize(prodInven);
							service.updateInven(oi);
						}
					}
					service.addOrderOption(op);				
				}
			}
			for(int z = 0; z < cartNo.length; z++) {
				c.setCartNo(Integer.parseInt(cartNo[z]));
				cService.delCartItem(c);
			}
			m.put("orderList", ol);
	//		m.put("orderProdList", service.getOrderProd(ol.getOrderNo()));			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();			
		}
		return "sewProduct/sewCartOrderCheckout";
	}
	
	@PostMapping("/sewOrder.do")
	@Transactional
	public String order(Order o, OrderOption op, OrderList ol, OrderInven oi, HttpServletRequest r, HttpSession session, 
			HttpServletResponse response, ModelMap m){
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			Member mem = (Member)session.getAttribute("mem");
			String memId = mem.getMemId();
			MyPage mp = new MyPage();		
			mp.setMemId(memId);
			int accPoint = Integer.parseInt(r.getParameter("memAccPoint"));
			mp = mpService.getMemPoint(mp);
			int memPoint = mp.getMemPoint();		
			int sumPoint = memPoint-ol.getTotalUsedPoint()+accPoint;		
			OrderPoint omp = new OrderPoint();
			omp.setMemId(memId);
			omp.setMemPoint(sumPoint);		
			service.updateMemPoint(omp);
			
			String [] orderColor = r.getParameterValues("orderColor");
			String [] orderSize = r.getParameterValues("orderSize");
			String [] orderAmount = r.getParameterValues("orderAmount");
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			ol.setMemId(memId);
			ol.setReceiverContact(ol.getDeliTelNo1()+ol.getDeliTelNo2()+ol.getDeliTelNo3());		
			ol.setOrderDate(ts);
			service.addOrderList(ol);
			o.setOrderNo(ol.getOrderNo());
			
			service.addOrder(o);
			
			for(int i = 0; i < orderColor.length; i++) {						
				op = new OrderOption();
				op.setProdNo(o.getProdNo());
				op.setOrderProdNo(o.getOrderProdNo());
				op.setOrderColor(orderColor[i]);
				op.setOrderSize(orderSize[i]);
				op.setOrderAmount(Integer.parseInt(orderAmount[i]));
				oi = service.getInven(op);
				if(op.getOrderSize().equals("S 사이즈")) {
					int prodInven = oi.getProdSsize();
					if(prodInven == 0 || prodInven < op.getOrderAmount()) {
						out.println("<script>"
										+ "alert('품절된 상품입니다.'); "
										+ "location.href='myCart.do';"
								  + "</script>");
						out.flush();
						throw new Exception();							
					} else {
						prodInven = prodInven - op.getOrderAmount();
						oi.setProdSize("S 사이즈");
						oi.setProdSsize(prodInven);
						service.updateInven(oi);
					}					
				} else if(op.getOrderSize().equals("M 사이즈")) {
					int prodInven = oi.getProdMsize();
					if(prodInven == 0 || prodInven < op.getOrderAmount()) {
						out.println("<script>"
								+ "alert('품절된 상품입니다.'); "
								+ "location.href='myCart.do';"
						  + "</script>");
						out.flush();
						throw new Exception();
					} else {
						prodInven = prodInven - op.getOrderAmount();
						oi.setProdSize("M 사이즈");
						oi.setProdMsize(prodInven);
						service.updateInven(oi);
					}
				} else if(op.getOrderSize().equals("L 사이즈")) {
					int prodInven = oi.getProdLsize();
					if(prodInven == 0 || prodInven < op.getOrderAmount()) {
						out.println("<script>"
								+ "alert('품절된 상품입니다.'); "
								+ "location.href='myCart.do';"
						  + "</script>");
						out.flush();
						throw new Exception();
					} else {
						prodInven = prodInven - op.getOrderAmount();
						oi.setProdSize("L 사이즈");
						oi.setProdLsize(prodInven);
						service.updateInven(oi);
					}
				} else if(op.getOrderSize().equals("XL 사이즈")) {
					int prodInven = oi.getProdXLsize();
					if(prodInven == 0 || prodInven < op.getOrderAmount()) {
						out.println("<script>"
								+ "alert('품절된 상품입니다.'); "
								+ "location.href='myCart.do';"
						  + "</script>");
						out.flush();
						throw new Exception();
					} else {
						prodInven = prodInven - op.getOrderAmount();
						oi.setProdSize("XL 사이즈");
						oi.setProdXLsize(prodInven);
						service.updateInven(oi);
					}
				}
				service.addOrderOption(op);				
			}
			m.put("orderList", ol);
	//		m.put("orderProdList", service.getOrderProd(ol.getOrderNo()));			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return "sewProduct/sewDirectOrderCheckout";
	}
}

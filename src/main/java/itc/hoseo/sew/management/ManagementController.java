package itc.hoseo.sew.management;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class ManagementController {
	@Autowired
	ManagementService service;
	
	@GetMapping("/management.do")
	public String goManagement() {
		return "sewManagement/sewManagement";
	}
	@GetMapping("/sewSales.do")
	public String goSalesPage() {
		return "sewManagement/sewSalesTable";
	}
	@GetMapping("/sewAddProd.do")
	public String goAddProd() {
		return "sewManagement/sewAddProd";
	}
	// 메인 화면 새로운 제품
	@GetMapping("/")
	public String getNewProd(ModelMap m) {
		m.put("newProdList", service.getNewProd());
		return "/index";
	}
	@GetMapping("/sewProdDetail.do")
	public String goDetailPage() {
		return "sewProduct/sewProductDetail";
	}
	
	// 상품 추가
	@PostMapping("/addProd.do")
	public String addProd(Management m, HttpServletRequest r, MultipartHttpServletRequest mh) {
		int prodNo = 0;
		String prodGen = m.getProdGen();
		String prodType = m.getProdType();
		prodNo = service.addProd(m);
		if(prodGen.equals("남성")) {			
			m.setProdNo(prodNo);
			if(prodNo!=0) {
				if(prodType.equals("상의")) {
					service.addMenTopSize(m);
				} else {
					service.addMenBotSize(m);
				}				
				m = service.imgUpload(m, r, mh);
				m.setProdNo(prodNo);
				service.addProdImg(m);
				return "redirect:/management.do";
			}
		} else {
			m.setProdNo(prodNo);
			if(prodNo!=0) {
				if(prodType.equals("상의")) {
					service.addWomenTopSize(m);
				} else {
					service.addWomenBotSize(m);
				}
				m = service.imgUpload(m, r, mh);
				m.setProdNo(prodNo);
				service.addProdImg(m);
				return "redirect:/management.do";
			}
		}
		return "/sewAddProd.do"; 
	}	
}

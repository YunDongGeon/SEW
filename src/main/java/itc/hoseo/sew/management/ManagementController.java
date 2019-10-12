package itc.hoseo.sew.management;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ch.qos.logback.classic.Logger;

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
	@GetMapping("/sewDetail")
	public String goDetailPage(
			@RequestParam(value="prodNo") int prodNo,
			ModelMap m) {
		Management manage = new Management();
		manage.setProdNo(prodNo);
		manage = service.getProd(manage);
		m.put("prodDetail", manage);		
		m.put("prodOption", service.getOption(manage));
		return "sewProduct/sewProductDetail";
	}
	
	// 상품 추가
	@PostMapping("/addProd.do")
	public String addProd(Management m, HttpServletRequest r, MultipartHttpServletRequest mh) {
		int prodNo = 0;
		String prodType = m.getProdType();
		prodNo = service.addProd(m);
		m.setProdNo(prodNo);
		if(prodNo!=0) {
			service.addProdInven(m);
			m = service.imgUpload(m, r, mh);
			m.setProdNo(prodNo);
			service.addProdImg(m);
			return "redirect:/management.do";
		}
		return "/sewAddProd.do"; 
	}	
	@PostMapping("/testJson.do")
	@ResponseBody
	public void testJson(@RequestBody Map<String, Object> json) {
		
	}	
}

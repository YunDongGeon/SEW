package itc.hoseo.sew.management;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	@GetMapping("/sewDetail")
	public String goDetailPage(@RequestParam(value="prodNo") int prodNo, ModelMap m) {
		Management manage = new Management();
		manage.setProdNo(prodNo);
		manage = service.getProd(manage);
		m.put("prodDetail", manage);
		m.put("prodColor", service.getProdColor(manage));
		return "sewProduct/sewProductDetail";
	}
	
	@PostMapping("/getSize")
	@ResponseBody
	public Map<Object, Object> getSize(@RequestBody Management mg, HttpSession session) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("prodInven", service.getProdInven(mg));
		return map;
	}
	
	// 상품 추가
	@PostMapping("/addProd.do")
	public String addProd(Management m, HttpServletRequest r, MultipartHttpServletRequest mh) {		
		int prodNo = 0;
		String [] prodColor = r.getParameterValues("prodColor");
		
		String [] sSize = r.getParameterValues("prodSsize");				
		String [] mSize = r.getParameterValues("prodMsize");
		String [] lSize = r.getParameterValues("prodLsize");
		String [] xlSize = r.getParameterValues("prodXLsize");		
		int [] prodSsize = Arrays.stream(sSize).mapToInt(Integer::parseInt).toArray();
		int [] prodMsize = Arrays.stream(mSize).mapToInt(Integer::parseInt).toArray();
		int [] prodLsize = Arrays.stream(lSize).mapToInt(Integer::parseInt).toArray();
		int [] prodXLsize = Arrays.stream(xlSize).mapToInt(Integer::parseInt).toArray();
		
		String prodType = m.getProdType();
		service.addProd(m);
		prodNo = service.getMaxNo();
		m.setProdNo(prodNo);
		if(prodNo!=0) {
			m = service.imgUpload(m, r, mh);
			m.setProdNo(prodNo);			
			service.addProdImg(m);
			for (int i = 0; i < prodColor.length; i++) {
				m.setProdColor(prodColor[i]);
				m.setProdSsize(prodSsize[i]);
				m.setProdMsize(prodMsize[i]);
				m.setProdLsize(prodLsize[i]);
				m.setProdXLsize(prodXLsize[i]);
				service.addProdInven(m);
			}	
			return "redirect:/management.do";
		}
		return "/sewAddProd.do"; 
	}		
}

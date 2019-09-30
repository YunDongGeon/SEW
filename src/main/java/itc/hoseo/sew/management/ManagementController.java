package itc.hoseo.sew.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	@PostMapping("/addProd.do")
	public String addProd(Management m) {
		if(service.addMenProd(m)) {
			return "redirect:/management.do";
		}
		return "/sewAddProd.do"; 
	}
}

package itc.hoseo.sew.management;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
	@PostMapping("/addProd.do")
	public String addProd(Management m, HttpServletRequest request, MultipartHttpServletRequest multi) {
		Iterator<String> imgs = multi.getFileNames();
		String path = "C:\\Users\\dbseh\\Desktop\\3-2\\Spring\\SEW\\src\\main\\resources\\static";
		String folderName1 = "\\prodThumb\\";
		String folderName2 = "\\prodCont\\";        
        String thumb = "yes";
        
		while (imgs.hasNext()) {
			String uploadFile = imgs.next();			
			MultipartFile mFile = multi.getFile(uploadFile);
			String sourceFileName = mFile.getOriginalFilename();
			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
			File destinationFile; 
	        String destinationFileName;        
	        	        
	        
			do { 
	            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
	            if(thumb.equals("yes")) {	            	
	            	destinationFile = new File(path + folderName1 +destinationFileName);
	            	m.setProdThumb(destinationFileName);
	            	m.setProdThumbOriName(sourceFileName);
	            	m.setProdThumbUrl(path+folderName1);	
	            	thumb = "no";
	            } else {
	            	destinationFile = new File(path + folderName2 +destinationFileName);
	            	m.setProdCont(destinationFileName);
	            	m.setProdContOriName(sourceFileName);
	            	m.setProdContUrl(path+folderName2);
	            }
	        } while (destinationFile.exists()); 
	        
	        destinationFile.getParentFile().mkdirs(); 
	        try {
	        	mFile.transferTo(destinationFile);	        		            	           
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}        
		if(service.addMenProd(m)) {			
			return "redirect:/management.do";
		}
		return "/sewAddProd.do"; 
	}
}

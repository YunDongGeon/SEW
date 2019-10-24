package itc.hoseo.sew.management;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class ManagementService {
	@Autowired
	ManagementRepository managementRepository;
	
	public int addProd(Management m) {
		return managementRepository.addProd(m);
	}
	
	public int getMaxNo() {
		return managementRepository.getMaxNo();
	}
	
	public boolean addProdImg(Management m) {
		return managementRepository.addProdImg(m)!=0;
	}	
	
	public boolean addProdInven(Management m) {
		return managementRepository.addProdInven(m)!=0;
	}

	public List<Management> getNewProd() {
		return managementRepository.getNewProd();
	}
	
	public Management getProd(Management m) {
		return managementRepository.getProd(m);
	}
	
	public List<Management> getProdColor(Management m) {
		return managementRepository.getProdColor(m);
	}
	
	public Management getProdInven(Management m) {
		return managementRepository.getProdInven(m);
	}
	
	@Autowired
	private Environment env;
	
	public Management imgUpload(Management m, HttpServletRequest request, MultipartHttpServletRequest multi){
    	Iterator<String> imgs = multi.getFileNames();
		String path = env.getProperty("upload-folder");
		String folderName1 = "prodThumb/";
		String folderName2 = "prodCont/";        
        String thumb = "yes";            
        File destinationFile = null;
        MultipartFile mFile = null;     
        
		while (imgs.hasNext()) {
			String uploadFile = imgs.next();			
			mFile = multi.getFile(uploadFile);
			String sourceFileName = mFile.getOriginalFilename();
			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
	        //String destinationFileName;	        	        
			do { 
	            //destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
	            if(thumb.equals("yes")) {	            	
	            	destinationFile = new File(path + folderName1 +sourceFileName);
	            	m.setProdThumbName(sourceFileName);
	            	m.setProdThumbUrl(folderName1);	
	            	thumb = "no";
	            } else {
	            	destinationFile = new File(path + folderName2 +sourceFileName);
	            	m.setProdContName(sourceFileName);
	            	m.setProdContUrl(folderName2);
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
		return m;		
    }
}

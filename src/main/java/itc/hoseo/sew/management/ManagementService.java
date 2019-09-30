package itc.hoseo.sew.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementService {
	@Autowired
	ManagementRepository managementRepository;
	
	public boolean addMenProd(Management manage) {
		return managementRepository.addMenProd(manage)!=0;
	}
}

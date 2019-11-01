package itc.hoseo.sew.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository repo;
	
	public boolean addOrder(Payment p) {
		return repo.addOrder(p)!=0;
	}

}

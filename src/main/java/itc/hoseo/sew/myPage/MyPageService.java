package itc.hoseo.sew.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {
	@Autowired
	private MyPageRepository repo;
}

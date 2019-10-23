package itc.hoseo.sew.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {
	@Autowired
	private MyPageRepository repo;
	
	public MyPage getMemPoint(MyPage mp) {
		return repo.getMemPoint(mp);
	}
}

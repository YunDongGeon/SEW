package itc.hoseo.sew.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import itc.hoseo.sew.member.Member;
import itc.hoseo.sew.member.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepositoty;
	
	public boolean addMember(Member member) {
		return memberRepositoty.addMember(member)==1;
	}
	public int memIdChk(String memId) {					
		return memberRepositoty.memIdChk(memId);
	}
	public String getMember(String id) {					
		return memberRepositoty.getMember(id);
	}
	public int getMemberCount() {
		return memberRepositoty.getMemberCount();
	}
}

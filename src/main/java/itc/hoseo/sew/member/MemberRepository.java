package itc.hoseo.sew.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

@Mapper
public interface MemberRepository {
	public int addMember(Member member);
	public int memIdChk(String memId);
	public Member loginChk(Member member);
	public Member getMember(String memId);
	public int getMemberCount();
}

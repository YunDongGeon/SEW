package itc.hoseo.sew.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

@Mapper
public interface MemberRepository {
	public int addMember(Member member);
	public int memIdChk(String memId);
	public Member loginChk(Member member);
	public Member getMember(Member member);
	public Member findMemId(Member member);
	public Member findMemPw(Member member);
	public int updateTempPw(Member member);
	public int updateNewPw(Member member);
}


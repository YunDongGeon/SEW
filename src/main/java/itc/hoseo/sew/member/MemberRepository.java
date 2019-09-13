package itc.hoseo.sew.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
	public int addMember(Member member);
	public String getMember(String id);
	public int getMemberCount();
}

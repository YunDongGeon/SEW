package itc.hoseo.sew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import itc.hoseo.sew.member.Member;

public interface XMLConfigMemberMapper {
	public int addMember(Member member);	
	public String getMemberName(String id);	
	public int getMemberCount();
}

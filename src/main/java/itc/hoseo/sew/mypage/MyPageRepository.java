package itc.hoseo.sew.mypage;

import org.apache.ibatis.annotations.Mapper;

import itc.hoseo.sew.mypage.MyPage;

@Mapper
public interface MyPageRepository {
	public MyPage getMemPoint(MyPage mp);
}

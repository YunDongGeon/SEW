package itc.hoseo.sew.management;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagementRepository {
	public int addProd(Management m);
	public int addProdImg(Management m);
	public int addMenTopSize(Management m);
	public int addMenBotSize(Management m);
	public int addWomenTopSize(Management m);
	public int addWomenBotSize(Management m);	
	public List<Management> getNewProd();
	public List<Management> getNewProdImg();
}

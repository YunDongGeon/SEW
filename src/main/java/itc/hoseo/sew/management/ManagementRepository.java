package itc.hoseo.sew.management;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagementRepository {
	public int addMenProd(Management m);
	public int addMenTopSize(Management m);
	public int addMenBotSize(Management m);
	public int addMenProdImg(Management m);
	public int addWomenTopSize(Management m);
	public int addWomenBotSize(Management m);
	public int addWomenProd(Management m);
	public int addWomenProdImg(Management m);
}

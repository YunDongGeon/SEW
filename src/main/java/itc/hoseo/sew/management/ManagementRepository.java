package itc.hoseo.sew.management;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagementRepository {
	public int addProd(Management m);
	public int getMaxNo();
	public int addProdImg(Management m);
	public int addProdInven(Management m);
	public List<Management> getNewProd();
	public Management getProd(Management m);
	public Management getProdInven(Management m);	
}

package itc.hoseo.sew.management;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagementRepository {
	public int addMenProd(Management manage);
}

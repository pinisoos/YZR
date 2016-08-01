package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.EmpVO;

public interface EmpDAO {
	
	public List<EmpVO> getList();
	public void empInsert(int no) throws Exception;

}
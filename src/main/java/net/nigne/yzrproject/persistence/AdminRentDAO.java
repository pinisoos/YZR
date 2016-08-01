package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.RentVO;

public interface AdminRentDAO {
	
	public List<RentVO> getRentList(Criteria cri);
	public Long getRentCount();
	public void deleteRent(int no) throws Exception;
	public void rentComplete(int no) throws Exception;

}

package net.nigne.yzrproject.service;

import java.util.List;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.RentVO;

public interface AdminRentService {
	
	public List<RentVO> getRentList(Criteria cri);
	public Long getRentCount();
	public void deleteRent(int no) throws Exception;
	public void rentComplete(int no) throws Exception;
}

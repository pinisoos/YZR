package net.nigne.yzrproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.persistence.AdminRentDAO;

@Service
public class AdminRentServiceImpl implements AdminRentService {
	
	@Autowired
	private AdminRentDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<RentVO> getRentList(Criteria cri){
		// TODO Auto-generated method stub
		return dao.getRentList(cri);
	}
	@Transactional(readOnly=true)
	@Override
	public Long getRentCount() {
		// TODO Auto-generated method stub
		return dao.getRentCount();
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void deleteRent(int no) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteRent(no);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void rentComplete(int no) throws Exception {
		// TODO Auto-generated method stub
		dao.rentComplete(no);
	}
}

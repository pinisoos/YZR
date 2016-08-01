package net.nigne.yzrproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.persistence.AdminLostDAO;

@Service
public class AdminLostServiceImpl implements AdminLostService {

	@Autowired
	private AdminLostDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<LostVO> getLostList(Criteria cri){
		// TODO Auto-generated method stub
		return dao.getLostList(cri);
	}
	@Transactional(readOnly=true)
	@Override
	public List<LostVO> getSearchLostList(Criteria cri, String keyword, String search){
		// TODO Auto-generated method stub
		return dao.getSearchLostList(cri, keyword, search);
	}
	@Transactional(readOnly=true)
	@Override
	public Long getLostCount() {
		// TODO Auto-generated method stub
		return dao.getLostCount();
	}
	@Transactional(readOnly=true)
	@Override
	public Long getLostSearchCount(String keyword, String search) {
		// TODO Auto-generated method stub
		return dao.getLostSearchCount(keyword, search);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void deleteLost(int no) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteLost(no);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void lostComplete(int no) throws Exception {
		// TODO Auto-generated method stub
		dao.lostComplete(no);
	}
}

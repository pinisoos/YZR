package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.persistence.SupportLostDAO;

@Service
public class SupportLostServiceImpl implements SupportLostService {

	@Autowired
	private SupportLostDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public MemberVO getMember(String member_id) {
		// TODO Auto-generated method stub
		return dao.getMember(member_id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<String> getTheaterList(String theater_area) {
		// TODO Auto-generated method stub
		return dao.getTheaterList(theater_area);
	}
	@Transactional(readOnly=true)
	@Override
	public List<String> getAreaList() {
		// TODO Auto-generated method stub
		return dao.getAreaList();
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void persist(LostVO vo) {
		// TODO Auto-generated method stub
		dao.persist(vo);
	}
}

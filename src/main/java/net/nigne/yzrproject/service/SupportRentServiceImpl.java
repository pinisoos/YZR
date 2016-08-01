package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.persistence.SupportLostDAO;
import net.nigne.yzrproject.persistence.SupportRentDAO;

@Service
public class SupportRentServiceImpl implements SupportRentService {

	@Autowired
	private SupportRentDAO dao;
	
	@Autowired
	private SupportLostDAO ldao;
	
	@Transactional(readOnly=true)
	@Override
	public MemberVO getMember(String member_id) {
		// TODO Auto-generated method stub
		return ldao.getMember(member_id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<String> getTheaterList(String theater_area) {
		// TODO Auto-generated method stub
		return ldao.getTheaterList(theater_area);
	}
	@Transactional(readOnly=true)
	@Override
	public List<String> getAreaList() {
		// TODO Auto-generated method stub
		return ldao.getAreaList();
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void persist(RentVO vo) {
		// TODO Auto-generated method stub
		dao.persist(vo);
	}
	@Transactional(readOnly=true)
	@Override
	public List<String> getPlextypeList() {
		// TODO Auto-generated method stub
		return dao.getPlextypeList();
	}
	@Transactional(readOnly=true)
	@Override
	public List<String> getSpecialTheaterList(String theater_type) {
		// TODO Auto-generated method stub
		return dao.getSpecialTheaterList(theater_type);
	}
}

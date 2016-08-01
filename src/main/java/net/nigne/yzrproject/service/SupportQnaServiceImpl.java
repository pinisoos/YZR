package net.nigne.yzrproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.persistence.SupportLostDAO;
import net.nigne.yzrproject.persistence.SupportQnaDAO;
import net.nigne.yzrproject.persistence.SupportRentDAO;

@Service
public class SupportQnaServiceImpl implements SupportQnaService {

	@Autowired
	private SupportQnaDAO dao;
	
	@Autowired
	private SupportLostDAO ldao;
	
	@Transactional(readOnly=true)
	@Override
	public MemberVO getMember(String member_id) {
		// TODO Auto-generated method stub
		return ldao.getMember(member_id);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void persist(QnaVO vo) {
		// TODO Auto-generated method stub
		dao.persist(vo);
	}
}

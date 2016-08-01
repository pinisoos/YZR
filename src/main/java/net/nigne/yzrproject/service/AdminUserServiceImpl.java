package net.nigne.yzrproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.persistence.AdminUserDAO;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<MemberVO> getMemberList(Criteria cri){
		// TODO Auto-generated method stub
		return dao.getMemberList(cri);
	}
	@Transactional(readOnly=true)
	@Override
	public List<MemberVO> getSearchMemberList(Criteria cri, String keyword, String search){
		// TODO Auto-generated method stub
		return dao.getSearchMemberList(cri, keyword, search);
	}
	@Transactional(readOnly=true)
	@Override
	public Long getMemberCount() {
		// TODO Auto-generated method stub
		return dao.getMemberCount();
	}
	@Transactional(readOnly=true)
	@Override
	public Long getMemberSearchCount(String keyword, String search) {
		// TODO Auto-generated method stub
		return dao.getMemberSearchCount(keyword, search);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void deleteMember(String member_id) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteMember(member_id);
	}

}

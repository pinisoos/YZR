package net.nigne.yzrproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.persistence.LoginDAO;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDAO dao;
	
	@Transactional(readOnly=true, rollbackFor={Exception.class})
	@Override
	public MemberVO memberLogin(String member_id, String member_pw) throws Exception{
		// TODO Auto-generated method stub
		return dao.memberLogin(member_id, member_pw);
	}
}
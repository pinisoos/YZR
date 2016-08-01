package net.nigne.yzrproject.service;

import net.nigne.yzrproject.domain.MemberVO;

public interface LoginService {
	
	public MemberVO memberLogin(String member_id, String member_pw) throws Exception;
	
}

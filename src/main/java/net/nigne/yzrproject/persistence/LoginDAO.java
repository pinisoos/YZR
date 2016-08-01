package net.nigne.yzrproject.persistence;

import net.nigne.yzrproject.domain.MemberVO;

public interface LoginDAO {
	
	public MemberVO memberLogin(String member_id, String member_pw);
	
}

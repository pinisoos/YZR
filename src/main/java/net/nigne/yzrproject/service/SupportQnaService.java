package net.nigne.yzrproject.service;

import java.util.List;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;

public interface SupportQnaService {
	public MemberVO getMember(String member_id);
	public void persist(QnaVO vo);
}

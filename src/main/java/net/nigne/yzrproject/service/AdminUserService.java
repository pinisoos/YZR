package net.nigne.yzrproject.service;

import java.util.List;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MemberVO;

public interface AdminUserService {
	public List<MemberVO> getMemberList(Criteria cri);
	public void deleteMember(String member_id) throws Exception;
	public Long getMemberCount();
	public List<MemberVO> getSearchMemberList(Criteria cri, String keyword, String search);
	public Long getMemberSearchCount(String keyword, String search);
}

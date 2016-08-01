package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.MemberVO;

public interface AdminUserDAO {
	public List<MemberVO> getMemberList(Criteria cri);
	public Long getMemberCount();
	public void deleteMember(String member_id);
	public List<MemberVO> getSearchMemberList(Criteria cri, String keyword, String search);
	public Long getMemberSearchCount(String keyword, String search);
}

package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.TheaterVO;

public interface SupportLostDAO {
	public MemberVO getMember(String member_id);
	public List<String> getTheaterList(String theater_area);
	public List<String> getAreaList();
	public void persist(LostVO vo);

}

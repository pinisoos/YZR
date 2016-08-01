package net.nigne.yzrproject.service;

import java.util.List;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.RentVO;

public interface SupportRentService {
	public MemberVO getMember(String member_id);
	public List<String> getTheaterList(String theater_area);
	public List<String> getAreaList();
	public List<String> getPlextypeList();
	public void persist(RentVO vo);
	public List<String> getSpecialTheaterList(String theater_type);
}

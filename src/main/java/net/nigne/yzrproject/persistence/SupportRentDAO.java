package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.TheaterVO;

public interface SupportRentDAO {
	public void persist(RentVO vo);
	public List<String> getPlextypeList();
	public List<String> getSpecialTheaterList(String theater_type);

}

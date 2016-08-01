package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.TheaterVO;

public interface SupportQnaDAO {
	public void persist(QnaVO vo);

}

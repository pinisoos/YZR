package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.LostVO;

public interface AdminLostDAO {
	public List<LostVO> getLostList(Criteria cri);
	public void deleteLost(int no);
	public Long getLostCount();
	public List<LostVO> getSearchLostList(Criteria cri, String keyword, String search);
	public Long getLostSearchCount(String keyword, String search);
	public void lostComplete(int no) throws Exception;

}

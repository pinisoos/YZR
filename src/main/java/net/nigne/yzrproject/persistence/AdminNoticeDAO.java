package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.NoticeVO;

public interface AdminNoticeDAO {
	public List<NoticeVO> getNoticeList(Criteria cri);
	public void deleteNotice(int no);
	public Long getNoticeCount();
	public void persist(NoticeVO vo);
	public List<NoticeVO> getSearchNoticeList(Criteria cri, String keyword, String search);
	public Long getNoticeSearchCount(String keyword, String search);
	public NoticeVO getNoticeDetail(int no);
	public void updateNotice(NoticeVO vo);

}

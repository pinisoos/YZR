package net.nigne.yzrproject.service;

import java.util.List;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.NoticeVO;

public interface AdminNoticeService {
	public List<NoticeVO> getNoticeList(Criteria cri);
	public void deleteNotice(int no) throws Exception;
	public Long getNoticeCount();
	public void persist(NoticeVO vo) throws Exception;
	public List<NoticeVO> getSearchNoticeList(Criteria cri, String keyword, String search);
	public Long getNoticeSearchCount(String keyword, String search);
	public NoticeVO getNoticeDetail(int no);
	public void updateNotice(NoticeVO vo);
}

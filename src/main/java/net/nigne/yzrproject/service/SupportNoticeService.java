package net.nigne.yzrproject.service;

import java.util.List;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.NoticeVO;

public interface SupportNoticeService {
	public List<NoticeVO> getNoticeList(Criteria cri);
	public Long getNoticeCount();
	public List<NoticeVO> getSearchNoticeList(Criteria cri, String keyword, String search);
	public Long getNoticeSearchCount(String keyword, String search);
	public NoticeVO getNoticeDetail(int no);
}

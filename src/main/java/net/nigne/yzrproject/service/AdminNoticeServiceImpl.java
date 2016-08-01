package net.nigne.yzrproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.NoticeVO;
import net.nigne.yzrproject.persistence.AdminNoticeDAO;

@Service
public class AdminNoticeServiceImpl implements AdminNoticeService {

	@Autowired
	private AdminNoticeDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<NoticeVO> getNoticeList(Criteria cri){
		// TODO Auto-generated method stub
		return dao.getNoticeList(cri);
	}
	@Transactional(readOnly=true)
	@Override
	public List<NoticeVO> getSearchNoticeList(Criteria cri, String keyword, String search){
		// TODO Auto-generated method stub
		return dao.getSearchNoticeList(cri, keyword, search);
	}
	@Transactional(readOnly=true)
	@Override
	public Long getNoticeCount() {
		// TODO Auto-generated method stub
		return dao.getNoticeCount();
	}
	@Transactional(readOnly=true)
	@Override
	public Long getNoticeSearchCount(String keyword, String search) {
		// TODO Auto-generated method stub
		return dao.getNoticeSearchCount(keyword, search);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void deleteNotice(int no) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteNotice(no);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void persist(NoticeVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		dao.persist(vo);
	}
	@Transactional(readOnly=true)
	@Override
	public NoticeVO getNoticeDetail(int no) {
		// TODO Auto-generated method stub
		return dao.getNoticeDetail(no);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void updateNotice(NoticeVO vo) {
		// TODO Auto-generated method stub
		dao.updateNotice(vo);
	}

}

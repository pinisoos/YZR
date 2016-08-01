package net.nigne.yzrproject.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.NoticeVO;

@Repository
public class AdminNoticeDAOImpl implements AdminNoticeDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<NoticeVO> getNoticeList(Criteria cri) {
		// TODO Auto-generated method stub
		List<NoticeVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<NoticeVO> cq=cb.createQuery(NoticeVO.class);
		Root<NoticeVO> root = cq.from(NoticeVO.class);
		cq.select(root);
		cq.orderBy(cb.desc(root.get("no")));
		try{
			TypedQuery<NoticeVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public List<NoticeVO> getSearchNoticeList(Criteria cri, String keyword, String search) {
		// TODO Auto-generated method stub
		List<NoticeVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<NoticeVO> cq=cb.createQuery(NoticeVO.class);
		Root<NoticeVO> root = cq.from(NoticeVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get(keyword), search));
		cq.orderBy(cb.desc(root.get("no")));
		try{
			TypedQuery<NoticeVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public Long getNoticeCount() {
		// TODO Auto-generated method stub
		Long noticeTotal = 0L;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<NoticeVO> root = cq.from(NoticeVO.class);
		cq.select(cb.count(root));
		
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			noticeTotal=tq.getSingleResult();
			return noticeTotal;
		}catch(Exception e){
			return noticeTotal;
		}
	}
	@Override
	public Long getNoticeSearchCount(String keyword, String search) {
		// TODO Auto-generated method stub
		Long noticeSearchTotal = 0L;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<NoticeVO> root = cq.from(NoticeVO.class);
		cq.select(cb.count(root));
		cq.where(cb.equal(root.get(keyword), search));
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			noticeSearchTotal=tq.getSingleResult();
			return noticeSearchTotal;
		}catch(Exception e){
			return noticeSearchTotal;
		}
	}
	@Override
	public void deleteNotice(int no){
		// TODO Auto-generated method stub
		NoticeVO vo=entityManager.find(NoticeVO.class, no);
		entityManager.remove(vo);
	}
	@Override
	public void persist(NoticeVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
	@Override
	public NoticeVO getNoticeDetail(int no) {
		// TODO Auto-generated method stub
		NoticeVO vo = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<NoticeVO> cq=cb.createQuery(NoticeVO.class);
		Root<NoticeVO> root = cq.from(NoticeVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("no"), no));
		try{
			TypedQuery<NoticeVO> tq = entityManager.createQuery(cq);
			vo=tq.getSingleResult();
			return vo;
		}catch(Exception e){
			return vo;
		}
	}
	@Override
	public void updateNotice(NoticeVO vo) {
		// TODO Auto-generated method stub
		NoticeVO findvo=entityManager.find(NoticeVO.class, vo.getNo());
		NoticeVO mergevo=entityManager.merge(findvo);
		mergevo.setNotice_category(vo.getNotice_category());
		mergevo.setNotice_title(vo.getNotice_title());
		mergevo.setNotice_content(vo.getNotice_content());
	}
}

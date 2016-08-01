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
import net.nigne.yzrproject.domain.LostVO;

@Repository
public class AdminLostDAOImpl implements AdminLostDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<LostVO> getLostList(Criteria cri) {
		// TODO Auto-generated method stub
		List<LostVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<LostVO> cq=cb.createQuery(LostVO.class);
		Root<LostVO> root = cq.from(LostVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("complete"), "N"));
		cq.orderBy(cb.desc(root.get("no")));
		try{
			TypedQuery<LostVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public List<LostVO> getSearchLostList(Criteria cri, String keyword, String search) {
		// TODO Auto-generated method stub
		List<LostVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<LostVO> cq=cb.createQuery(LostVO.class);
		Root<LostVO> root = cq.from(LostVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get(keyword), search));
		cq.where(cb.equal(root.get("complete"), "N"));
		cq.orderBy(cb.desc(root.get("no")));
		try{
			TypedQuery<LostVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public Long getLostCount() {
		// TODO Auto-generated method stub
		Long lostTotal = 0L;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<LostVO> root = cq.from(LostVO.class);
		cq.select(cb.count(root));
		cq.where(cb.equal(root.get("complete"), "N"));
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			lostTotal=tq.getSingleResult();
			return lostTotal;
		}catch(Exception e){
			return lostTotal;
		}
	}
	@Override
	public Long getLostSearchCount(String keyword, String search) {
		// TODO Auto-generated method stub
		Long lostSearchTotal = 0L;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<LostVO> root = cq.from(LostVO.class);
		cq.select(cb.count(root));
		cq.where(cb.equal(root.get(keyword), search));
		cq.where(cb.equal(root.get("complete"), "N"));
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			lostSearchTotal=tq.getSingleResult();
			return lostSearchTotal;
		}catch(Exception e){
			return lostSearchTotal;
		}
	}
	@Override
	public void deleteLost(int no){
		// TODO Auto-generated method stub
		LostVO vo=entityManager.find(LostVO.class, no);
		entityManager.remove(vo);
	}
	@Override
	public void lostComplete(int no){
		// TODO Auto-generated method stub
		LostVO vo=entityManager.find(LostVO.class, no);
		LostVO mergevo=entityManager.merge(vo);
		mergevo.setComplete("Y");
	}
}

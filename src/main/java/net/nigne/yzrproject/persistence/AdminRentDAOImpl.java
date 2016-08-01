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
import net.nigne.yzrproject.domain.RentVO;

@Repository
public class AdminRentDAOImpl implements AdminRentDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<RentVO> getRentList(Criteria cri) {
		// TODO Auto-generated method stub
		List<RentVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<RentVO> cq=cb.createQuery(RentVO.class);
		Root<RentVO> root = cq.from(RentVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("complete"), "N"));
		cq.orderBy(cb.desc(root.get("no")));
		try{
			TypedQuery<RentVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(5);
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public Long getRentCount() {
		// TODO Auto-generated method stub
		Long rentTotal = 0L;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<RentVO> root = cq.from(RentVO.class);
		cq.select(cb.count(root));
		cq.where(cb.equal(root.get("complete"), "N"));
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			rentTotal=tq.getSingleResult();
			return rentTotal;
		}catch(Exception e){
			return rentTotal;
		}
	}
	@Override
	public void deleteRent(int no){
		// TODO Auto-generated method stub
		RentVO vo=entityManager.find(RentVO.class, no);
		entityManager.remove(vo);
	}
	@Override
	public void rentComplete(int no){
		// TODO Auto-generated method stub
		RentVO vo=entityManager.find(RentVO.class, no);
		RentVO mergevo=entityManager.merge(vo);
		mergevo.setComplete("Y");
	}
}

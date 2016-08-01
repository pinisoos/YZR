package net.nigne.yzrproject.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.TheaterVO;

@Repository
public class SupportRentDAOImpl implements SupportRentDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void persist(RentVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
	@Override
	public List<String> getPlextypeList() {
		// TODO Auto-generated method stub
		List<String> list=null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<PlexVO> root = cq.from(PlexVO.class);
		cq.select(root.get("plex_type")).distinct(true);
		cq.where(cb.notEqual(root.get("plex_type"), "2D"));
		cq.orderBy(cb.asc(root.get("plex_type")));
		TypedQuery<String> tq = entityManager.createQuery(cq);
		list=tq.getResultList();
		
		return list;
	}
	@Override
	public List<String> getSpecialTheaterList(String theater_type) {
		// TODO Auto-generated method stub
		List<String> list=null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<PlexVO> root = cq.from(PlexVO.class);
		cq.select(root.get("theater_id")).distinct(true);
		cq.where(cb.equal(root.get("plex_type"), theater_type));
		cq.orderBy(cb.asc(root.get("theater_id")));
		TypedQuery<String> tq = entityManager.createQuery(cq);
		list=tq.getResultList();
		
		List<Predicate> p = new ArrayList<Predicate>();
		CriteriaQuery<String> subcq=cb.createQuery(String.class);
		Root<TheaterVO> subroot = subcq.from(TheaterVO.class);
		for(int a=0; a<list.size(); a++){
            p.add(cb.equal(subroot.get("theater_id"), list.get(a)));
        }
		subcq.where(cb.or(p.toArray(new Predicate[p.size()])));
		subcq.select(subroot.get("theater_name"));
		TypedQuery<String> subtq = entityManager.createQuery(subcq);
		list=subtq.getResultList();
		
		return list;
	}
}

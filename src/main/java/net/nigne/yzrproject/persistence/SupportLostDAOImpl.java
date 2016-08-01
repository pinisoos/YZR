package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.TheaterVO;

@Repository
public class SupportLostDAOImpl implements SupportLostDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public MemberVO getMember(String member_id) {
		// TODO Auto-generated method stub
		MemberVO vo=new MemberVO();
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberVO> cq=cb.createQuery(MemberVO.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("member_id"), member_id));
		TypedQuery<MemberVO> tq = entityManager.createQuery(cq);
		vo=tq.getSingleResult();
		
		return vo;
	}
	@Override
	public List<String> getTheaterList(String theater_area) {
		// TODO Auto-generated method stub
		List<String> list=null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<TheaterVO> root = cq.from(TheaterVO.class);
		cq.select(root.get("theater_name"));
		cq.where(cb.equal(root.get("theater_area"), theater_area));
		TypedQuery<String> tq = entityManager.createQuery(cq);
		list=tq.getResultList();
		
		return list;
	}
	@Override
	public List<String> getAreaList() {
		// TODO Auto-generated method stub
		List<String> list=null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<TheaterVO> root = cq.from(TheaterVO.class);
		cq.select(root.get("theater_area")).distinct(true);
		TypedQuery<String> tq = entityManager.createQuery(cq);
		list=tq.getResultList();
		
		return list;
	}
	@Override
	public void persist(LostVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
}

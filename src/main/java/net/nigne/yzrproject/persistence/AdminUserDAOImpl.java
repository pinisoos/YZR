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
import net.nigne.yzrproject.domain.MemberVO;

@Repository
public class AdminUserDAOImpl implements AdminUserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<MemberVO> getMemberList(Criteria cri) {
		// TODO Auto-generated method stub
		List<MemberVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberVO> cq=cb.createQuery(MemberVO.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root);
		cq.orderBy(cb.desc(root.get("member_id")));
		try{
			TypedQuery<MemberVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public List<MemberVO> getSearchMemberList(Criteria cri, String keyword, String search) {
		// TODO Auto-generated method stub
		List<MemberVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberVO> cq=cb.createQuery(MemberVO.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get(keyword), search));
		cq.orderBy(cb.desc(root.get("member_id")));
		try{
			TypedQuery<MemberVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public Long getMemberCount() {
		// TODO Auto-generated method stub
		Long memberTotal = 0L;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(cb.count(root));
		
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			memberTotal=tq.getSingleResult();
			return memberTotal;
		}catch(Exception e){
			return memberTotal;
		}
	}
	@Override
	public Long getMemberSearchCount(String keyword, String search) {
		// TODO Auto-generated method stub
		Long memberSearchTotal = 0L;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		cq.select(cb.count(root));
		cq.where(cb.equal(root.get(keyword), search));
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			memberSearchTotal=tq.getSingleResult();
			return memberSearchTotal;
		}catch(Exception e){
			return memberSearchTotal;
		}
	}
	@Override
	public void deleteMember(String member_id){
		// TODO Auto-generated method stub
		MemberVO vo=entityManager.find(MemberVO.class, member_id);
		entityManager.remove(vo);
	}
}

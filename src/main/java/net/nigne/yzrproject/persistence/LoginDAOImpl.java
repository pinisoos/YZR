package net.nigne.yzrproject.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import net.nigne.yzrproject.domain.MemberVO;

@Repository
public class LoginDAOImpl implements LoginDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public MemberVO memberLogin(String member_id, String member_pw) {
		// TODO Auto-generated method stub
		MemberVO vo = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MemberVO> cq=cb.createQuery(MemberVO.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		Predicate restrictions = cb.equal(root.get("member_id"), member_id);
		cq.where(restrictions);
		try{
			TypedQuery<MemberVO> tq = entityManager.createQuery(cq);
			vo=tq.getSingleResult();
			if(!vo.getMember_pw().equals(member_pw)){
				vo=null;
			}
			return vo;
		}catch(Exception e){
			return vo;
		}
	}
}

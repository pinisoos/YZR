/** 
*
*/
package net.nigne.yzrproject.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.MovieVO;

/** 
* @FileName : UserInfoDAOImpl.java 
* @Package  : net.nigne.yzrproject.persistence 
* @Date     : 2016. 7. 28. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

	@PersistenceContext
	EntityManager em;
	/** 
	* @Method Name	: getMemberInfo 
	* @Method 설명	: 
	* @param member_id
	* @return 
	*/
	@Override
	public MemberVO getMemberInfo(String member_id) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MemberVO> cq = cb.createQuery(MemberVO.class);
		Root<MemberVO> root = cq.from(MemberVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.select(root).where(p);
		TypedQuery<MemberVO> tq = em.createQuery(cq);
		MemberVO vo= tq.getSingleResult();
		
		return vo;
	}

}

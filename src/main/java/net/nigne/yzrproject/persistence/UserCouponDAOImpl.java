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

import net.nigne.yzrproject.domain.CouponVO;
import net.nigne.yzrproject.domain.Criteria;

/** 
* @FileName : UserCouponDAOImpl.java 
* @Package  : net.nigne.yzrproject.persistence 
* @Date     : 2016. 7. 28. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
@Repository
public class UserCouponDAOImpl implements UserCouponDAO {

	@PersistenceContext
	EntityManager entityManager;
	/** 
	* @Method Name	: getCouponTotal 
	* @Method 설명	: 
	* @param member_id
	* @return 
	*/
	@Override
	public long getCouponTotal(String member_id) {
	
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<CouponVO> root = cq.from(CouponVO.class);
		//사용자의 쿠폰
		Predicate p = cb.equal(root.get("member_id"), member_id);
		//사용하지 않은
		Predicate p2 = cb.equal(root.get("used"), "n");
		
		cq.select(cb.count(root)).where(cb.and(p,p2));

		
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		long couponTotal = tq.getSingleResult();
		
		return couponTotal;
	}
	@Override
	public List<CouponVO> getCouponList(Criteria cri, String member_id) {
		// TODO Auto-generated method stub
		List<CouponVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<CouponVO> cq=cb.createQuery(CouponVO.class);
		Root<CouponVO> root = cq.from(CouponVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("member_id"), member_id));
		cq.orderBy(cb.asc(root.get("used")), cb.asc(root.get("no")));
		try{
			System.out.println(cri.getStartPage());
			TypedQuery<CouponVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}

}

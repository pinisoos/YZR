/** 
*
*/
package net.nigne.yzrproject.persistence;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.CouponVO;

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
	EntityManager em;
	/** 
	* @Method Name	: getCouponTotal 
	* @Method 설명	: 
	* @param member_id
	* @return 
	*/
	@Override
	public long getCouponTotal(String member_id) {
		//현재시간
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		String today = sdf.format(dt).toString();
        
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<CouponVO> root = cq.from(CouponVO.class);
		//사용자의 쿠폰
		Predicate p = cb.equal(root.get("member_id"), member_id);
		//사용하지 않은
		Predicate p2 = cb.equal(root.get("used"), "n");
		//기한이 지나지 않은 
        Predicate p3 = cb.greaterThan(root.get("coupon_date"), today);
		cq.select(cb.count(root)).where(cb.and(p,p2,p3));
		
		TypedQuery<Long> tq = em.createQuery(cq);
		long couponTotal = tq.getSingleResult();
		
		return couponTotal;
	}

}

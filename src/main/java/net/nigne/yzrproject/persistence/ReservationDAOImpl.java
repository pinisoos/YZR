package net.nigne.yzrproject.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import net.nigne.yzrproject.domain.CouponVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.ReservationVO;

@Repository
public class ReservationDAOImpl implements ReservationDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ReservationVO> getReservation_list(String member_id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ReservationVO> cq = cb.createQuery(ReservationVO.class);
		Root<ReservationVO> root = cq.from(ReservationVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.where(p);
		
		TypedQuery<ReservationVO> tq = entityManager.createQuery(cq);
		List<ReservationVO> list = tq.getResultList();
		return list;
	}

	/** 
	* @Method Name	: getReservation 
	* @Method 설명	: member_id로 reservation_list 테이블에 있는 예매내역, reservation_list 테이블에 있는 movie_id로 movie테이블에서 예매했던 영화정보 가져옴 
	* @param member_id
	* @return 
	*/
	@Override
	public Map<String,Object> getReservation(String member_id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ReservationVO> cq = cb.createQuery(ReservationVO.class);
		Root<ReservationVO> root = cq.from(ReservationVO.class);
		Predicate p = cb.equal(root.get("member_id"), member_id);
		cq.where(p).orderBy(cb.desc(root.get("reservation_date")));
		
		TypedQuery<ReservationVO> tq = entityManager.createQuery(cq);
		List<ReservationVO> list = tq.getResultList();
	
		List<Predicate> pl = new ArrayList<Predicate>();
		
		CriteriaQuery<MovieVO> movieQuery = cb.createQuery(MovieVO.class);
        Root<MovieVO> movieRoot = movieQuery.from(MovieVO.class);
        movieQuery.select(movieRoot);
        movieQuery.where(movieRoot.get("movie_id"));
       
        for(int a=0; a<list.size(); a++){
        	pl.add(cb.or(cb.equal(movieRoot.get("movie_id"), list.get(a).getMovie_id())));
        }
        movieQuery.where(cb.or(pl.toArray(new Predicate[pl.size()])));
		
        TypedQuery<MovieVO> movieTq = entityManager.createQuery(movieQuery);
		List<MovieVO> movieList = movieTq.getResultList();
		//select title,movie_id from movie where movie_id = (select movie_id from reservation_list where member_id = member_id)
		
		Map<String,Object> map = new HashMap<>();
		map.put("reservationList", list);
		map.put("reservationMovie", movieList);
		
		return map;
	}

	/** 
	* @Method Name	: getReservationTotal 
	* @Method 설명	: 
	* @param member_id
	* @return 
	*/
	@Override
	public long getReservationTotal(String member_id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ReservationVO> root = cq.from(ReservationVO.class);
		//사용자와 일치하는 예매내역
		Predicate p = cb.equal(root.get("member_id"), member_id);
		
		cq.select(cb.count(root)).where(p);
		
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		long reservationTotal = tq.getSingleResult();
		
		return reservationTotal;
	}

}

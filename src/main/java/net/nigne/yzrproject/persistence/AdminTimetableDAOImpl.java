package net.nigne.yzrproject.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;

@Repository
public class AdminTimetableDAOImpl implements AdminTimetableDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TheaterVO> getTheatername() {
		// TODO Auto-generated method stub
		List<TheaterVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<TheaterVO> cq=cb.createQuery(TheaterVO.class);
		Root<TheaterVO> root = cq.from(TheaterVO.class);
		cq.select(root);
		try{
			TypedQuery<TheaterVO> tq = entityManager.createQuery(cq);
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public List<String> getPlexnumber() {
		// TODO Auto-generated method stub
		List<String> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<PlexVO> root = cq.from(PlexVO.class);
		cq.select(root.get("plex_number"));
		cq.distinct(true);
		cq.orderBy(cb.asc(root.get("plex_number")));
		try{
			TypedQuery<String> tq = entityManager.createQuery(cq);
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public void persist(TimetableVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
	@Override
	public List<String> getStartDay(String plex_number, String theater_id) {
		// TODO Auto-generated method stub
		List<String> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<TimetableVO> root = cq.from(TimetableVO.class);
		cq.select(cb.substring(root.get("start_time"), 1, 10));
		cq.where(cb.equal(root.get("theater_id"), theater_id), cb.equal(root.get("plex_number"), plex_number));
		try{
			TypedQuery<String> tq = entityManager.createQuery(cq);
			list=tq.getResultList();
			
			list = new ArrayList<>(new HashSet<String>(list));
			
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public List<TimetableVO> getStartTime(String theater_id, String plex_number, String startday) {
		// TODO Auto-generated method stub
		List<TimetableVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<TimetableVO> cq=cb.createQuery(TimetableVO.class);
		Root<TimetableVO> root = cq.from(TimetableVO.class);
		cq.select(root);
		cq.where(cb.like(root.get("start_time"), "%" + startday + "%"), cb.equal(root.get("theater_id"), theater_id), cb.equal(root.get("plex_number"), plex_number));
		cq.orderBy(cb.asc(root.get("start_time")));
		try{
			TypedQuery<TimetableVO> tq = entityManager.createQuery(cq);
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public String getMoviename(String movie_id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String movie_name = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq=cb.createQuery(String.class);
		Root<MovieVO> root = cq.from(MovieVO.class);
		cq.select(root.get("title"));
		cq.where(cb.equal(root.get("movie_id"), movie_id));
		try{
			TypedQuery<String> tq = entityManager.createQuery(cq);
			movie_name=tq.getSingleResult();
			return movie_name;
		}catch(Exception e){
			return movie_name;
		}
	}
}

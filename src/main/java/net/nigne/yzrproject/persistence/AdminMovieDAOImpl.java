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
import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.NoticeVO;

@Repository
public class AdminMovieDAOImpl implements AdminMovieDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<MovieVO> getMovieList(Criteria cri) {
		// TODO Auto-generated method stub
		List<MovieVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MovieVO> cq=cb.createQuery(MovieVO.class);
		Root<MovieVO> root = cq.from(MovieVO.class);
		cq.select(root);
		cq.orderBy(cb.desc(root.get("movie_id")));
		try{
			TypedQuery<MovieVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public List<MovieVO> getSearchMovieList(Criteria cri, String keyword, String search) {
		// TODO Auto-generated method stub
		List<MovieVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MovieVO> cq=cb.createQuery(MovieVO.class);
		Root<MovieVO> root = cq.from(MovieVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get(keyword), search));
		cq.orderBy(cb.desc(root.get("movie_id")));
		try{
			TypedQuery<MovieVO> tq = entityManager.createQuery(cq).setFirstResult(cri.getStartPage()).setMaxResults(cri.getArticlePerPage());
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
	@Override
	public Long getMovieCount() {
		// TODO Auto-generated method stub
		Long movieTotal = 0L;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<MovieVO> root = cq.from(MovieVO.class);
		cq.select(cb.count(root));
		
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			movieTotal=tq.getSingleResult();
			return movieTotal;
		}catch(Exception e){
			return movieTotal;
		}
	}
	@Override
	public Long getMovieSearchCount(String keyword, String search) {
		// TODO Auto-generated method stub
		Long movieSearchTotal = 0L;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq=cb.createQuery(Long.class);
		Root<MovieVO> root = cq.from(MovieVO.class);
		cq.select(cb.count(root));
		cq.where(cb.equal(root.get(keyword), search));
		try{
			TypedQuery<Long> tq = entityManager.createQuery(cq);
			movieSearchTotal=tq.getSingleResult();
			return movieSearchTotal;
		}catch(Exception e){
			return movieSearchTotal;
		}
	}
	@Override
	public void deleteMovie(String movie_id){
		// TODO Auto-generated method stub
		MovieVO vo=entityManager.find(MovieVO.class, movie_id);
		entityManager.remove(vo);
	}
	@Override
	public void persist(MovieVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
	@Override
	public void persistGenre(GenreVO gvo) {
		// TODO Auto-generated method stub
		entityManager.persist(gvo);
	}
	@Override
	public void statusMovie(String movie_id, String status) {
		// TODO Auto-generated method stub
		MovieVO findvo=entityManager.find(MovieVO.class, movie_id);
		MovieVO mergevo=entityManager.merge(findvo);
		mergevo.setStatus(status);
	}
}

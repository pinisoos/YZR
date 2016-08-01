package net.nigne.yzrproject.persistence;

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

@Repository
public class AdminEtcDAOImpl implements AdminEtcDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void actorPersist(ActorVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
	@Override
	public void directorPersist(DirectorVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
	@Override
	public List<MovieVO> getMoviename() {
		// TODO Auto-generated method stub
		List<MovieVO> list = null;
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<MovieVO> cq=cb.createQuery(MovieVO.class);
		Root<MovieVO> root = cq.from(MovieVO.class);
		cq.select(root);
		try{
			TypedQuery<MovieVO> tq = entityManager.createQuery(cq);
			list=tq.getResultList();
			return list;
		}catch(Exception e){
			return list;
		}
	}
}

package net.nigne.yzrproject.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import net.nigne.yzrproject.domain.LostVO;
import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.QnaVO;
import net.nigne.yzrproject.domain.RentVO;
import net.nigne.yzrproject.domain.TheaterVO;

@Repository
public class SupportQnaDAOImpl implements SupportQnaDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void persist(QnaVO vo) {
		// TODO Auto-generated method stub
		entityManager.persist(vo);
	}
}

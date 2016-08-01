package net.nigne.yzrproject.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import net.nigne.yzrproject.domain.EmpVO;

@Repository
public class EmpDAOImpl implements EmpDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<EmpVO> getList() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<EmpVO> cq=cb.createQuery(EmpVO.class);
		Root<EmpVO> root = cq.from(EmpVO.class);
		cq.select(root);
		TypedQuery<EmpVO> tq = entityManager.createQuery(cq);
		List<EmpVO> list = tq.getResultList();
		
		return list;
	}
	@Override
	public void empInsert(int no) throws Exception{
		// TODO Auto-generated method stub
		EmpVO vo=new EmpVO();
		vo.setEmp_no(no);
		vo.setEmp_name(""+no);
		vo.setAddress(""+no);
		entityManager.persist(vo);
	}
}
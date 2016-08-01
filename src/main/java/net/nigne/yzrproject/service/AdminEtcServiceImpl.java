package net.nigne.yzrproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.persistence.AdminEtcDAO;

@Service
public class AdminEtcServiceImpl implements AdminEtcService {

	@Autowired
	private AdminEtcDAO dao;
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void actorPersist(ActorVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.actorPersist(vo);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void directorPersist(DirectorVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.directorPersist(vo);
	}
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> getMoviename() {
		// TODO Auto-generated method stub
		return dao.getMoviename();
	}
}

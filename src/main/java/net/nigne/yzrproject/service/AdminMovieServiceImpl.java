package net.nigne.yzrproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.persistence.AdminMovieDAO;

@Service
public class AdminMovieServiceImpl implements AdminMovieService {

	@Autowired
	private AdminMovieDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> getMovieList(Criteria cri){
		// TODO Auto-generated method stub
		return dao.getMovieList(cri);
	}
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> getSearchMovieList(Criteria cri, String keyword, String search){
		// TODO Auto-generated method stub
		return dao.getSearchMovieList(cri, keyword, search);
	}
	@Transactional(readOnly=true)
	@Override
	public Long getMovieCount() {
		// TODO Auto-generated method stub
		return dao.getMovieCount();
	}
	@Transactional(readOnly=true)
	@Override
	public Long getMovieSearchCount(String keyword, String search) {
		// TODO Auto-generated method stub
		return dao.getMovieSearchCount(keyword, search);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void deleteMovie(String movie_id) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteMovie(movie_id);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void persist(MovieVO vo, GenreVO gvo) throws Exception {
		// TODO Auto-generated method stub
		dao.persist(vo);
		dao.persistGenre(gvo);
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void statusMovie(String movie_id, String status) throws Exception {
		// TODO Auto-generated method stub
		dao.statusMovie(movie_id, status);
	}
}

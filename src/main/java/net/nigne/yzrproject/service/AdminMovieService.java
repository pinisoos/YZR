package net.nigne.yzrproject.service;

import java.util.List;
import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;

public interface AdminMovieService {
	public List<MovieVO> getMovieList(Criteria cri);
	public void deleteMovie(String movie_id) throws Exception;
	public Long getMovieCount();
	public void persist(MovieVO vo, GenreVO gvo) throws Exception;
	public List<MovieVO> getSearchMovieList(Criteria cri, String keyword, String search);
	public Long getMovieSearchCount(String keyword, String search);
	public void statusMovie(String movie_id,String status) throws Exception;
}

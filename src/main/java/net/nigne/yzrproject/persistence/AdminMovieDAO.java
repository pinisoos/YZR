package net.nigne.yzrproject.persistence;

import java.util.List;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;

public interface AdminMovieDAO {
	public List<MovieVO> getMovieList(Criteria cri);
	public void deleteMovie(String movie_id);
	public Long getMovieCount();
	public void persist(MovieVO vo);
	public List<MovieVO> getSearchMovieList(Criteria cri, String keyword, String search);
	public Long getMovieSearchCount(String keyword, String search);
	void persistGenre(GenreVO gvo);
	public void statusMovie(String movie_id,String status);

}

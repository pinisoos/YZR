package net.nigne.yzrproject.service;

import java.util.List;
import java.util.Map;

import net.nigne.yzrproject.domain.GenreVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.PlexVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;

public interface TimetableService {
	public Map<String,Object> getTimetable(String theater_id, String day);
	public List<MovieVO> getMovieInfo(String theater_id, String day);
	public List<GenreVO> getMovieGenre(String theater_id, String day);
	public List<PlexVO> getPlexInfo(String theater_id, String day);
	public List<TheaterVO> getLike_theater(String member_id);
	public List<TimetableVO> getList(String movie, String theater, String date, String plex_number);
	public List<String> getPlexNum(String movie, String theater, String date);
}

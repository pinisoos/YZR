package net.nigne.yzrproject.service;

import java.util.List;
import java.util.Map;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;

public interface AdminTimetableService {
	public List<MovieVO> getAllMoviename();
	public List<TheaterVO> getTheatername();
	public List<String> getPlexnumber();
	public void persist(TimetableVO vo) throws Exception;
	public List<String> getStartDay(String plex_number, String theater_id);
	public Map<String, Object> getStartTime(String theater_id, String plex_number, String startday);

}

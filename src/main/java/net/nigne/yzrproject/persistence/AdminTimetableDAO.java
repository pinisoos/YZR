package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;

public interface AdminTimetableDAO {
	public List<TheaterVO> getTheatername();
	public List<String> getPlexnumber();
	public void persist(TimetableVO vo);
	public List<String> getStartDay(String plex_number, String theater_id);
	public List<TimetableVO> getStartTime(String theater_id, String plex_number, String startday);
	public String getMoviename(String movie_id);

}

package net.nigne.yzrproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;
import net.nigne.yzrproject.domain.TheaterVO;
import net.nigne.yzrproject.domain.TimetableVO;
import net.nigne.yzrproject.persistence.AdminEtcDAO;
import net.nigne.yzrproject.persistence.AdminTimetableDAO;

@Service
public class AdminTimetableServiceImpl implements AdminTimetableService {

	@Autowired
	private AdminEtcDAO dao;
	@Autowired
	private AdminTimetableDAO tdao;
	
	@Transactional(readOnly=true)
	@Override
	public List<MovieVO> getAllMoviename() {
		// TODO Auto-generated method stub
		return dao.getMoviename();
	}
	@Transactional(readOnly=true)
	@Override
	public List<TheaterVO> getTheatername() {
		// TODO Auto-generated method stub
		return tdao.getTheatername();
	}
	@Transactional(readOnly=true)
	@Override
	public List<String> getPlexnumber() {
		// TODO Auto-generated method stub
		return tdao.getPlexnumber();
	}
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void persist(TimetableVO vo) throws Exception {
		// TODO Auto-generated method stub
		tdao.persist(vo);
	}
	@Transactional(readOnly=true)
	@Override
	public List<String> getStartDay(String plex_number, String theater_id) {
		// TODO Auto-generated method stub
		return tdao.getStartDay(plex_number, theater_id);
	}
	@Transactional(readOnly=true)
	@Override
	public Map<String, Object> getStartTime(String theater_id, String plex_number, String startday) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		List<TimetableVO> list=tdao.getStartTime(theater_id, plex_number, startday);
		String movie_name=tdao.getMoviename(list.get(0).getMovie_id());
		map.put("starttime_list", list);
		map.put("movie_name", movie_name);
		return map;
	}
}

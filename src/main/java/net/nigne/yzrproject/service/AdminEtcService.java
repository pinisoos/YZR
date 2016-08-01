package net.nigne.yzrproject.service;

import java.util.List;

import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;

public interface AdminEtcService {
	public void actorPersist(ActorVO vo) throws Exception;
	public void directorPersist(DirectorVO vo) throws Exception;
	public List<MovieVO> getMoviename();
}

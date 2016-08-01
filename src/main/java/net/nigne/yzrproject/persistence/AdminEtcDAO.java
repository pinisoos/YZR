package net.nigne.yzrproject.persistence;

import java.util.List;
import net.nigne.yzrproject.domain.ActorVO;
import net.nigne.yzrproject.domain.DirectorVO;
import net.nigne.yzrproject.domain.MovieVO;

public interface AdminEtcDAO {
	public void actorPersist(ActorVO vo) throws Exception;
	public void directorPersist(DirectorVO vo) throws Exception;
	public List<MovieVO> getMoviename();
}

package net.nigne.yzrproject.service;

import java.util.List;
import java.util.Map;

import net.nigne.yzrproject.domain.ReservationVO;

public interface ReservationService {
	public List<ReservationVO> getReservation_list(String member_id);
	public Map<String,Object> getReservation(String member_id);
	public long getReservationTotal(String member_id);
}

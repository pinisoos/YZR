package net.nigne.yzrproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.ReservationVO;
import net.nigne.yzrproject.persistence.ReservationDAO;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationDAO dao;
	
	@Transactional(readOnly=true)
	@Override
	public List<ReservationVO> getReservation_list(String member_id) {
		return dao.getReservation_list(member_id);
	}

	/** 
	* @Method Name	: getReservation 
	* @Method 설명	: 
	* @param member_id
	* @return 
	*/
	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getReservation(String member_id) {

		return dao.getReservation(member_id);
	}

	/** 
	* @Method Name	: getReservationTotal 
	* @Method 설명	: 
	* @param member_id
	* @return 
	*/
	@Override
	public long getReservationTotal(String member_id) {
		
		return dao.getReservationTotal(member_id);
	}

}

/** 
*
*/
package net.nigne.yzrproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.persistence.UserCouponDAO;

/** 
* @FileName : UserCouponServiceImpl.java 
* @Package  : net.nigne.yzrproject.service 
* @Date     : 2016. 7. 28. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
@Service
public class UserCouponServiceImpl implements UserCouponService {

	@Autowired
	UserCouponDAO dao;
	
	/** 
	* @Method Name	: getCouponTotal 
	* @Method 설명	: 
	* @param member_id
	* @return 
	*/
	@Override
	@Transactional(readOnly = true)
	public long getCouponTotal(String member_id) {
		
		return dao.getCouponTotal(member_id);
	}

}

/** 
*
*/
package net.nigne.yzrproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.yzrproject.domain.MemberVO;
import net.nigne.yzrproject.persistence.UserInfoDAO;

/** 
* @FileName : UserInfoServiceImpl.java 
* @Package  : net.nigne.yzrproject.service 
* @Date     : 2016. 7. 28. 
* @�ۼ���		: ���뼺
* @���α׷� 	: ����...
*/
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	UserInfoDAO dao;
	
	/** 
	* @Method Name	: getMemberInfo 
	* @Method ����	: 
	* @param member_id
	* @return 
	*/
	@Override
	@Transactional
	public MemberVO getMemberInfo(String member_id) {
		
		return dao.getMemberInfo(member_id);
	}

}

/** 
*
*/
package net.nigne.yzrproject.persistence;

import net.nigne.yzrproject.domain.MemberVO;

/** 
* @FileName : UserInfoDAO.java 
* @Package  : net.nigne.yzrproject.persistence 
* @Date     : 2016. 7. 28. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
public interface UserInfoDAO {
	public MemberVO getMemberInfo(String member_id);
}

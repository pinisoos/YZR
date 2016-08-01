/** 
*
*/
package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
* @FileName : CouponVO.java 
* @Package  : net.nigne.yzrproject.domain 
* @Date     : 2016. 7. 28. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
@Entity
@Table(name="coupon")
public class CouponVO {

	/*----------------Columns----------------*/
	
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="COUPON_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="member_id")
	String member_id;
	
	@Column(name="coupon_code")
	String coupon_code;
	
	@Column(name="coupon_date")
	String coupon_date;
	
	@Column(name="coupon_amount")
	int coupon_amount;
	
	@Column(name="used")
	String used;
	
	@Column(name="publisher")
	String publisher;

	
	/*----------Getters and Setters----------*/
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getCoupon_code() {
		return coupon_code;
	}

	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}

	public String getCoupon_date() {
		return coupon_date;
	}

	public void setCoupon_date(String coupon_date) {
		this.coupon_date = coupon_date;
	}

	public int getCoupon_amount() {
		return coupon_amount;
	}

	public void setCoupon_amount(int coupon_amount) {
		this.coupon_amount = coupon_amount;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
}

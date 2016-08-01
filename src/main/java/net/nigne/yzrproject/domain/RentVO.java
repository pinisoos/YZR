package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="rent")
public class RentVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="RENT_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="member_id")
	String member_id;
	
	@Column(name="rent_place")
	String rent_place;
	
	@Column(name="rent_date")
	String rent_date;
	
	@Column(name="rent_content")
	String rent_content;
	
	@Column(name="complete")
	String complete;

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

	public String getRent_date() {
		return rent_date;
	}

	public void setRent_date(String rent_date) {
		this.rent_date = rent_date;
	}

	public String getRent_content() {
		return rent_content;
	}

	public void setRent_content(String rent_content) {
		this.rent_content = rent_content;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public String getRent_place() {
		return rent_place;
	}

	public void setRent_place(String rent_place) {
		this.rent_place = rent_place;
	}
}
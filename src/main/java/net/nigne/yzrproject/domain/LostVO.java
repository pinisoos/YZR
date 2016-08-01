package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="lost")
public class LostVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="LOST_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="member_id")
	String member_id;
	
	@Column(name="lost_place")
	String lost_place;
	
	@Column(name="lost_date")
	String lost_date;
	
	@Column(name="lost_type")
	String lost_type;
	
	@Column(name="lost_content")
	String lost_content;
	
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

	public String getLost_place() {
		return lost_place;
	}

	public void setLost_place(String lost_place) {
		this.lost_place = lost_place;
	}

	public String getLost_date() {
		return lost_date;
	}

	public void setLost_date(String lost_date) {
		this.lost_date = lost_date;
	}

	public String getLost_type() {
		return lost_type;
	}

	public void setLost_type(String lost_type) {
		this.lost_type = lost_type;
	}

	public String getLost_content() {
		return lost_content;
	}

	public void setLost_content(String lost_content) {
		this.lost_content = lost_content;
	}
	
	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}
}
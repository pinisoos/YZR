package net.nigne.yzrproject.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="notice")
public class NoticeVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="NOTICE_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="notice_category")
	String notice_category;
	
	@Column(name="notice_title")
	String notice_title;
	
	@Column(name="notice_date")
	String notice_date;
	
	@Column(name="notice_view_cnt")
	int notice_view_cnt;
	
	@Column(name="notice_content")
	String notice_content;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getNotice_category() {
		return notice_category;
	}

	public void setNotice_category(String notice_category) {
		this.notice_category = notice_category;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}

	public int getNotice_view_cnt() {
		return notice_view_cnt;
	}

	public void setNotice_view_cnt(int notice_view_cnt) {
		this.notice_view_cnt = notice_view_cnt;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
}
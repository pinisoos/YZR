package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="plex")
public class PlexVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="PLEX_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="theater_id")
	String theater_id;
	
	@Column(name="plex_number")
	String plex_number;
	
	@Column(name="plex_type")
	String plex_type;
	
	@Column(name="plex_seat_cnt")
	String plex_seat_cnt;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTheater_id() {
		return theater_id;
	}

	public void setTheater_id(String theater_id) {
		this.theater_id = theater_id;
	}

	public String getPlex_number() {
		return plex_number;
	}

	public void setPlex_number(String plex_number) {
		this.plex_number = plex_number;
	}

	public String getPlex_type() {
		return plex_type;
	}

	public void setPlex_type(String plex_type) {
		this.plex_type = plex_type;
	}

	public String getPlex_seat_cnt() {
		return plex_seat_cnt;
	}

	public void setPlex_seat_cnt(String plex_seat_cnt) {
		this.plex_seat_cnt = plex_seat_cnt;
	}
}
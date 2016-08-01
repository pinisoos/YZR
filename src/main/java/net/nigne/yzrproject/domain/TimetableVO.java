package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="timetable")
public class TimetableVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="TIMETABLE_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="movie_id")
	String movie_id;
	
	@Column(name="theater_id")
	String theater_id;
	
	@Column(name="plex_number")
	String plex_number;
	
	@Column(name="start_time")
	String start_time;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
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

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
}
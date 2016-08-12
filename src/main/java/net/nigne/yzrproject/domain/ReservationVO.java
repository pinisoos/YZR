package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservation_list")
public class ReservationVO {
	@Id
	@Column(name="reservation_code")
	String reservation_code;
	
	@Column(name="member_id")
	String member_id;
	
	@Column(name="movie_id")
	String movie_id;
	
	@Column(name="theater_id")
	String theater_id;
	
	@Column(name="plex_number")
	String plex_number;
	
	@Column(name="start_time")
	String start_time;
	
	@Column(name="ticket_cnt")
	int ticket_cnt;
	
	@Column(name="view_seat")
	String view_seat;
	
	@Column(name="pay")
	int pay;
	
	@Column(name="pay_method")
	String pay_method;
	
	@Column(name="reservation_date")
	String reservation_date;

	public String getReservation_code() {
		return reservation_code;
	}

	public void setReservation_code(String reservation_code) {
		this.reservation_code = reservation_code;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
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

	public int getTicket_cnt() {
		return ticket_cnt;
	}

	public void setTicket_cnt(int ticket_cnt) {
		this.ticket_cnt = ticket_cnt;
	}

	public String getView_seat() {
		return view_seat;
	}

	public void setView_seat(String view_seat) {
		this.view_seat = view_seat;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}
	
}

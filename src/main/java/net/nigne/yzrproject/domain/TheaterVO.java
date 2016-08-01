package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="theater")
public class TheaterVO {
	
	@Id
	@Column(name="theater_id")
	String theater_id;
	
	@Column(name="theater_name")
	String theater_name;
	
	@Column(name="theater_address")
	String theater_address;
	
	@Column(name="theater_area")
	String theater_area;
	
	@Column(name="area_code")
	int area_code;
	
	@Column(name="xpoint")
	String xpoint;
	
	@Column(name="ypoint")
	String ypoint;
	
	public String getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(String theater_id) {
		this.theater_id = theater_id;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public String getTheater_address() {
		return theater_address;
	}
	public void setTheater_address(String theater_address) {
		this.theater_address = theater_address;
	}
	public String getTheater_area() {
		return theater_area;
	}
	public void setTheater_area(String theater_area) {
		this.theater_area = theater_area;
	}
	public int getArea_code() {
		return area_code;
	}
	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}
	public String getXpoint() {
		return xpoint;
	}
	public void setXpoint(String xpoint) {
		this.xpoint = xpoint;
	}
	public String getYpoint() {
		return ypoint;
	}
	public void setYpoint(String ypoint) {
		this.ypoint = ypoint;
	}
}
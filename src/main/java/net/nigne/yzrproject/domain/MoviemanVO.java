package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movieman")
public class MoviemanVO {
	@Id
	@Column(name="movieman_id")
	String movieman_id;
	
	@Column(name="movieman_name")
	String movieman_name;
	
	@Column(name="movieman_age")
	String movieman_age;
	
	@Column(name="movieman_photo")
	String movieman_photo;
	
	@Column(name="movieman_debut")
	String movieman_debut;
	
	@Column(name="movieman_company")
	String movieman_company;
	
	@Column(name="isactor")
	String isactor;

	public String getMovieman_id() {
		return movieman_id;
	}

	public void setMovieman_id(String movieman_id) {
		this.movieman_id = movieman_id;
	}

	public String getMovieman_name() {
		return movieman_name;
	}

	public void setMovieman_name(String movieman_name) {
		this.movieman_name = movieman_name;
	}

	public String getMovieman_age() {
		return movieman_age;
	}

	public void setMovieman_age(String movieman_age) {
		this.movieman_age = movieman_age;
	}

	public String getMovieman_photo() {
		return movieman_photo;
	}

	public void setMovieman_photo(String movieman_photo) {
		this.movieman_photo = movieman_photo;
	}

	public String getMovieman_debut() {
		return movieman_debut;
	}

	public void setMovieman_debut(String movieman_debut) {
		this.movieman_debut = movieman_debut;
	}

	public String getMovieman_company() {
		return movieman_company;
	}

	public void setMovieman_company(String movieman_company) {
		this.movieman_company = movieman_company;
	}

	public String getIsactor() {
		return isactor;
	}

	public void setIsactor(String isactor) {
		this.isactor = isactor;
	}
}
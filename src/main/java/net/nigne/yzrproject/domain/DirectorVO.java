package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="director")
public class DirectorVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="DIRECTOR_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="movie_id")
	String movie_id;
	
	@Column(name="director_name")
	String director_name;
	
	@Column(name="director_age")
	String director_age;
	
	@Column(name="director_photo")
	String director_photo;
	
	@Column(name="director_debut")
	String director_debut;
	
	@Column(name="director_company")
	String director_company;
	
	public DirectorVO(String director_name, String director_age, String director_photo){
		this.director_name = director_name;
		this.director_age = director_age;
		this.director_photo = director_photo;
	}
	
	public DirectorVO(){
		
	}

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

	public String getDirector_name() {
		return director_name;
	}

	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}

	public String getDirector_age() {
		return director_age;
	}

	public void setDirector_age(String director_age) {
		this.director_age = director_age;
	}

	public String getDirector_photo() {
		return director_photo;
	}

	public void setDirector_photo(String director_photo) {
		this.director_photo = director_photo;
	}

	public String getDirector_debut() {
		return director_debut;
	}

	public void setDirector_debut(String director_debut) {
		this.director_debut = director_debut;
	}

	public String getDirector_company() {
		return director_company;
	}

	public void setDirector_company(String director_company) {
		this.director_company = director_company;
	}
}
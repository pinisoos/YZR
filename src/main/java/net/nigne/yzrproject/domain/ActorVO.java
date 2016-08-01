package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="actor")
public class ActorVO {
	@Id
	@GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="NoSequence", sequenceName="ACTOR_NO_SEQ", allocationSize=1)
	@Column(name="no")
	int no;
	
	@Column(name="movie_id")
	String movie_id;
	
	@Column(name="actor_name")
	String actor_name;
	
	@Column(name="actor_age")
	String actor_age;
	
	@Column(name="actor_photo")
	String actor_photo;
	
	@Column(name="actor_debut")
	String actor_debut;
	
	@Column(name="actor_company")
	String actor_company;
	
	public ActorVO(String actor_name, String actor_age, String actor_photo){
		this.actor_name = actor_name;
		this.actor_age = actor_age;
		this.actor_photo = actor_photo;
	}
	
	public ActorVO(){
		
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

	public String getActor_name() {
		return actor_name;
	}

	public void setActor_name(String actor_name) {
		this.actor_name = actor_name;
	}

	public String getActor_age() {
		return actor_age;
	}

	public void setActor_age(String actor_age) {
		this.actor_age = actor_age;
	}

	public String getActor_photo() {
		return actor_photo;
	}

	public void setActor_photo(String actor_photo) {
		this.actor_photo = actor_photo;
	}

	public String getActor_debut() {
		return actor_debut;
	}

	public void setActor_debut(String actor_debut) {
		this.actor_debut = actor_debut;
	}

	public String getActor_company() {
		return actor_company;
	}

	public void setActor_company(String actor_company) {
		this.actor_company = actor_company;
	}
}
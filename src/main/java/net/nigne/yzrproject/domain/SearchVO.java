/** 
*
*/
package net.nigne.yzrproject.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

/** 
* @FileName : SearchVO.java 
* @Package  : net.nigne.yzrproject.domain 
* @Date     : 2016. 7. 15. 
* @작성자		: 강대성
* @프로그램 	: 설명...
*/
@Entity
@Table(name = "movie")
@SecondaryTables({@SecondaryTable(name="actor", pkJoinColumns=@PrimaryKeyJoinColumn(name="movie_id")),
				  @SecondaryTable(name="director", pkJoinColumns=@PrimaryKeyJoinColumn(name="movie_id")),
				  @SecondaryTable(name="genre", pkJoinColumns=@PrimaryKeyJoinColumn(name="movie_id"))})
public class SearchVO {
	
	/*----------------Columns----------------*/
	
	/*-------------Movie Table---------------*/
	@Id
	@Column(name = "movie_id", table="movie")
	private String movie_id;
	
	@Column(name = "title", table="movie")
	private String title;
	
	@Column(name = "open_date", table="movie")
	private String open_date;
	
	@Column(name = "runtime", table="movie")
	private int runtime;
	
	//등급
	@Column(name = "rating", table="movie")
	private String rating;
	
	@Column(name = "moviegoers_cnt", table="movie")
	private int moviegoers_cnt;
	
	@Column(name = "reservation_rate", table="movie")
	private String reservation_rate;
	
	@Column(name = "story", table="movie")
	private String story;

	@Column(name = "site", table="movie")
	private String site;
	
	@Column(name = "poster", table="movie")
	private String poster;
	
	@Column(name = "status", table="movie")
	private String status;
	
	
	/*-------------Actor table---------------*/
	@Column(name = "no", table="actor")
	private int actor_no;
	
	@Column(name = "actor_name", table="actor")
	private String actor_name;
	
	@Column(name = "actor_age", table="actor")
	private String actor_age;
	
	@Column(name = "actor_photo", table="actor")
	private String actor_photo;
	
	@Column(name="actor_debut", table="actor")
	private String actor_debut;
	
	@Column(name="actor_company", table="actor")
	private String actor_company;
	
	
	/*------------Director table-------------*/
	@Column(name = "no", table="director")
	private int director_no;
	
	@Column(name = "director_name", table="director")
	private String director_name;
	
	@Column(name = "director_age", table="director")
	private String director_age;
	
	@Column(name = "director_photo", table="director")
	private String director_photo;
	
	@Column(name="director_debut", table="director")
	private String director_debut;
	
	@Column(name="director_company", table="director")
	private String director_company;
	
	/*-------------Genre table---------------*/
	@Column(name = "no", table="genre")
	private int genre_no;
	
	@Column(name="movie_genre", table="genre")
	private String movie_genre;
	
	/*--------------Constructor--------------*/
	public SearchVO(String movie_id, String b){
		this.movie_id = movie_id;
		this.actor_name = b;
		this.director_name = b;
		this.movie_genre = b;
	}
	public SearchVO(String movie_id, String title, String name){
		
		this.movie_id = movie_id;
		this.title = title;
		this.actor_name = name;
		this.director_name = name;
	}
	
		
	/*----------Getters and Setters----------*/
	
	//Actor table
	public int getActor_no() {
		return actor_no;
	}

	public void setActor_no(int actor_no) {
		this.actor_no = actor_no;
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

	
	//Director table
	public int getDirector_no() {
		return director_no;
	}

	public void setDirector_no(int director_no) {
		this.director_no = director_no;
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

	
	//Movie table
	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpen_date() {
		return open_date;
	}

	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getMoviegoers_cnt() {
		return moviegoers_cnt;
	}

	public void setMoviegoers_cnt(int moviegoers_cnt) {
		this.moviegoers_cnt = moviegoers_cnt;
	}

	public String getReservation_rate() {
		return reservation_rate;
	}

	public void setReservation_rate(String reservation_rate) {
		this.reservation_rate = reservation_rate;
	}

	public String getStory() {
		return story;
	}


	public void setStory(String story) {
		this.story = story;
	}


	public String getSite() {
		return site;
	}


	public void setSite(String site) {
		this.site = site;
	}


	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//Genre table
	public int getGenre_no() {
		return genre_no;
	}

	public void setGenre_no(int genre_no) {
		this.genre_no = genre_no;
	}

	public String getMovie_genre() {
		return movie_genre;
	}

	public void setMovie_genre(String movie_genre) {
		this.movie_genre = movie_genre;
	}
	
	
	
	
}

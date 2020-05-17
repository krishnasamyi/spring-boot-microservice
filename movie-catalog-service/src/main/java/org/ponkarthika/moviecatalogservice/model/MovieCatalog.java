package org.ponkarthika.moviecatalogservice.model;

public class MovieCatalog {

	public String userId;
	public String movieId;
	public String movie;
	public String rating;
	
	public MovieCatalog(String userId, String movieId, String movie, String rating) {
		super();
		this.userId = userId;
		this.movieId = movieId;
		this.movie = movie;
		this.rating = rating;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
}

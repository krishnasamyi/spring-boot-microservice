package org.ponkarthika.moviecatalogservice.model;

public class MovieRating {

	private String userId;
	private String movieId;
	private String rating;
	
	public MovieRating() {
		
	}
	
	public MovieRating(String userId, String movieId, String rating) {
		super();
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	

}

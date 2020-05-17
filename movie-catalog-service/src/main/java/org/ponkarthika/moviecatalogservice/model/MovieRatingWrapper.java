package org.ponkarthika.moviecatalogservice.model;

import java.util.List;

public class MovieRatingWrapper {

	private List<MovieRating> movieRatings;

	public MovieRatingWrapper() {
		super();
	}

	public MovieRatingWrapper(List<MovieRating> movieRatings) {
		super();
		this.movieRatings = movieRatings;
	}

	public List<MovieRating> getMovieRatings() {
		return movieRatings;
	}

	public void setMovieRatings(List<MovieRating> movieRatings) {
		this.movieRatings = movieRatings;
	}

}

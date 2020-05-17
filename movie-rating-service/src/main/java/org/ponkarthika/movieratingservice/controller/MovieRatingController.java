package org.ponkarthika.movieratingservice.controller;

import java.util.Arrays;
import java.util.List;

import org.ponkarthika.movieratingservice.model.MovieRating;
import org.ponkarthika.movieratingservice.model.MovieRatingWrapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movieRatings")
public class MovieRatingController {

	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "Hello";
	}

	@RequestMapping(value = "/{userId}/{movieId}", method = RequestMethod.GET)
	public MovieRating getRating(@PathVariable("userId") String userId,@PathVariable("movieId") String movieId) {
		return new MovieRating("Samy", "123", "7");
	}
	
	@RequestMapping(value ="/{userId}")
	public MovieRatingWrapper getMovieRatings(@PathVariable("userId")String userId) {
		List<MovieRating> movieRatings = Arrays.asList(new MovieRating(userId, "123", "5"),
				new MovieRating(userId, "133", "4"),
				new MovieRating(userId, "122", "3"));
		
		MovieRatingWrapper movieRatingWrapper = new MovieRatingWrapper();
		movieRatingWrapper.setMovieRatings(movieRatings);
		
		return movieRatingWrapper;
	}
}

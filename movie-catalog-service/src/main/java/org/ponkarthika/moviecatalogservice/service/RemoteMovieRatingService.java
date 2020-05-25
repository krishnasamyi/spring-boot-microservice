package org.ponkarthika.moviecatalogservice.service;

import java.util.Arrays;

import org.ponkarthika.moviecatalogservice.model.MovieRating;
import org.ponkarthika.moviecatalogservice.model.MovieRatingWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RemoteMovieRatingService {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getMovieRatingsFallback")
	public MovieRatingWrapper getMovieRatings(String userId) {
		MovieRatingWrapper movieRatingWrapper = restTemplate
				.getForObject("http://movie-rating-service/movieRatings/" + userId, MovieRatingWrapper.class);
		return movieRatingWrapper;
	}
	
	private MovieRatingWrapper getMovieRatingsFallback(String userId) {
		return new MovieRatingWrapper(Arrays.asList(new MovieRating(userId, "0", "Rating not found")));
	}
}

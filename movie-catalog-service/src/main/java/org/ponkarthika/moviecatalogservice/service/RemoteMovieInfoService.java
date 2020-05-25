package org.ponkarthika.moviecatalogservice.service;

import org.ponkarthika.moviecatalogservice.model.MovieInfo;
import org.ponkarthika.moviecatalogservice.model.MovieRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RemoteMovieInfoService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getMovieInfoFallback")
	public MovieInfo getMovieInfo(MovieRating movieRating) {
		MovieInfo movie = restTemplate.getForObject("http://movie-info-service/movie/" + movieRating.getMovieId(),
				MovieInfo.class);
		return movie;
	}
	
	private MovieInfo getMovieInfoFallback(MovieRating movieRating) {
		return new MovieInfo("0", "Movie not found");
	}
}

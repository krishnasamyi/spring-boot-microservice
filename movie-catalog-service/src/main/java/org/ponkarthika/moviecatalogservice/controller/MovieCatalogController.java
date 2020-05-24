package org.ponkarthika.moviecatalogservice.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.ponkarthika.moviecatalogservice.model.MovieCatalog;
import org.ponkarthika.moviecatalogservice.model.MovieInfo;
import org.ponkarthika.moviecatalogservice.model.MovieRating;
import org.ponkarthika.moviecatalogservice.model.MovieRatingWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/catalog")
public class MovieCatalogController {

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "Hello";
	}

	/*
	 * @RequestMapping(value = "/{userId}") public List<MovieCatalog>
	 * getMovieCatalog(@PathVariable("userId") String userId) { return
	 * Collections.singletonList(new MovieCatalog("123", "Samy", "Sarkar", "8")); }
	 */

	@RequestMapping(value = "/{userId}")
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50") }, fallbackMethod = "getCatalogsFallback")
	public List<MovieCatalog> getCatalogs(@PathVariable("userId") String userId) {
		/*
		 * List<MovieInfo> movies = Arrays.asList(new MovieInfo("123", "Sarkar"), new
		 * MovieInfo("133", "Darbar"), new MovieInfo("222", "Petta"));
		 */

		/*
		 * Rest template approach return movies.stream().map(movie -> { MovieRating
		 * movieRating = restTemplate.getForObject(
		 * "http://localhost:8083/movieRatings/" + userId + "/" + movie.getMovieId(),
		 * MovieRating.class); return new MovieCatalog(userId,movie.getMovieId(),
		 * movie.getMovieName(), movieRating.getRating());
		 * }).collect(Collectors.toList());
		 * 
		 */

		/* Rest template approach */
		MovieRatingWrapper movieRatingWrapper = restTemplate
				.getForObject("http://movie-rating-service/movieRatings/" + userId, MovieRatingWrapper.class);

		/*
		 * MovieRatingWrapper movieRatingWrapper = webClientBuilder.build().get()
		 * .uri("http://localhost:8083/movieRatings/" +
		 * userId).retrieve().bodyToMono(MovieRatingWrapper.class) .block();
		 */

		return movieRatingWrapper.getMovieRatings().stream().map(movieRating -> {
			MovieInfo movie = restTemplate.getForObject("http://movie-info-service/movie/" + movieRating.getMovieId(),
					MovieInfo.class);
			return new MovieCatalog(userId, movieRating.getMovieId(), movie.getMovieName(), movieRating.getRating());
		}).collect(Collectors.toList());
	}

	public List<MovieCatalog> getCatalogsFallback(@PathVariable("userId") String userId) {
		return Arrays.asList(new MovieCatalog(userId, "", "Movie not found", "0"));
	}

}

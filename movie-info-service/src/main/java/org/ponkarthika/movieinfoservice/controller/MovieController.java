package org.ponkarthika.movieinfoservice.controller;

import org.ponkarthika.movieinfoservice.model.MovieInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "Hello";
	}
	
	@RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
	public MovieInfo getMovieInfo(@PathVariable("movieId") String movieId) {
		return new MovieInfo("123", "Sarkar");
	}
}

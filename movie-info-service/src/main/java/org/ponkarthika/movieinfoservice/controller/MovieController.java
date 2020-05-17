package org.ponkarthika.movieinfoservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.ponkarthika.movieinfoservice.model.MovieDBInfo;
import org.ponkarthika.movieinfoservice.model.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${moviedb.api.url}")
	private String movieDbUrl;
	
	@Value("${moviedb.api.key}")
	private String movieDbApiKey;

	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "Hello";
	}
	
	@RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
	public MovieInfo getMovieInfo(@PathVariable("movieId") String movieId) {
		//return new MovieInfo("123", "Sarkar");
		/*Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("api_key", movieDbApiKey);
		MovieDBInfo movieDBInfo = restTemplate.getForObject(movieDbUrl+movieId, MovieDBInfo.class, urlParameters);*/
		String url = movieDbUrl + movieId + "?api_key=" + movieDbApiKey;
		MovieDBInfo movieDBInfo = restTemplate.getForObject(url, MovieDBInfo.class);
		
		MovieInfo movieInfo = null;
		if(movieDBInfo != null) {
			movieInfo = new MovieInfo(movieDBInfo.getId(), movieDBInfo.getTitle());
		}
		return movieInfo;
	}
}

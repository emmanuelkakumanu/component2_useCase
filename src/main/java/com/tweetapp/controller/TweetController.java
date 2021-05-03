package com.tweetapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.TweetDTO;
import com.tweetapp.model.Tweets;
import com.tweetapp.service.TweetService;

import io.swagger.annotations.Api;
@Api
@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "http://localhost:4200")
public class TweetController {
	public static final Logger LOGGER = LoggerFactory.getLogger(TweetController.class);
	@Autowired
	private TweetService tweetService;

	@GetMapping("/all")
	public List<Tweets> getAllTweets() {
		return tweetService.getAllTweets();
	}

	@GetMapping("/{username}/all")
	public List<Tweets> getAllUserTweets(@PathVariable String username) {
		return tweetService.getAllUserTweets(username);

	}

	@PutMapping("/{username}/like/{id}")
	public void likeTweet(@PathVariable("username") String username, @PathVariable("id") long id,
			@RequestBody TweetDTO tweetDTO) {
		LOGGER.debug("sldsd: " + tweetDTO);
		tweetService.likeTweet(tweetDTO, username, id);
	}

	@DeleteMapping("/{username}/delete/{id}")
	public void deleteTweet(@PathVariable("username") String username, @PathVariable("id") long id) {
		tweetService.deleteTweet(id);
	}

	@GetMapping("/{username}/add/{tweet}")
	public void postTweet(@PathVariable("username") String loginId, @PathVariable("tweet") String tweet) {
		tweetService.postTweet(loginId, tweet);

	}

}

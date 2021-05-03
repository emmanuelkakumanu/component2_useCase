package com.tweetapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.TweetDTO;
import com.tweetapp.model.Tweets;
import com.tweetapp.repository.TweetRepository;

@Service
public class TweetService {
	@Autowired
	private TweetRepository tweetRepository;

	@Transactional
	public List<Tweets> getAllTweets() {
		return tweetRepository.findAll();
	}

	@Transactional
	public List<Tweets> getAllUserTweets(String username) {
		return tweetRepository.getAllUserTweets(username);
	}

	@Transactional
	public void likeTweet(TweetDTO tweetDTO, String username, long id) {
//		tweetRepository.updateLikeCount(username, id);
		Tweets updateTweet = tweetRepository.getOne(id);
		updateTweet.setLikeCount(tweetDTO.getLikeCount());
		tweetRepository.save(updateTweet);
//		tweetRepository.updateLikeCount(tweetDTO.getLikeCount(),username, id);
	}

	@Transactional
	public void deleteTweet(Long id) {
		tweetRepository.deleteById(id);
	}

	@Transactional
	public void postTweet(String loginId, String tweet) {
		tweetRepository.postTweet(loginId, tweet, 0);

	}

}

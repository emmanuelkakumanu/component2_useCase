package com.tweetapp.dto;

import java.sql.Timestamp;

public class TweetDTO {
	long id;
	long likeCount;
	String loginId;
	Timestamp postedOn;
	String tweet;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Timestamp getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Timestamp postedOn) {
		this.postedOn = postedOn;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public TweetDTO(long id, long likeCount, String loginId, Timestamp postedOn, String tweet) {
		super();
		this.id = id;
		this.likeCount = likeCount;
		this.loginId = loginId;
		this.postedOn = postedOn;
		this.tweet = tweet;
	}

	public TweetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}

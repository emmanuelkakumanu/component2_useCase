package com.tweetapp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tweets")
public class Tweets {
	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "loginid")
	private String loginId;

	@Column(name = "tweet")
	private String tweet;

	@Column(name = "like_count")
	private long likeCount;

	@Column(name = "posted_on")
	private Timestamp postedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

	public Timestamp getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Timestamp postedOn) {
		this.postedOn = postedOn;
	}

	public Tweets(int id, String loginId, String tweet, int likeCount, Timestamp postedOn) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.tweet = tweet;
		this.likeCount = likeCount;
		this.postedOn = postedOn;
	}

	public Tweets() {
		super();
		// TODO Auto-generated constructor stub
	}

}

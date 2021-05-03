package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Tweets;

@Repository
public interface TweetRepository extends JpaRepository<Tweets, Long> {

	@Modifying
	@Query(value = "update tweets set like_count=?1 where loginid=?2 and id=?3", nativeQuery = true)
	public void updateLikeCount(long likeCount, String username, long id);

	@Query(value = "select * from tweets where loginid=?1", nativeQuery = true)
	public List<Tweets> getAllUserTweets(String username);
	
	@Modifying
	@Query(value = "insert into tweets (loginid,tweet,like_count) values(?1,?2,?3)", nativeQuery = true)
	public void postTweet(String loginId, String tweet, long likeCount);

}

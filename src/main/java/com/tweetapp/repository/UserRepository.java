package com.tweetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "select * from users where email=?1", nativeQuery = true)
	public User getUserByEmail(String email);

	@Query(value = "select * from users where loginid=?1", nativeQuery = true)
	public User getUserByLoginID(String loginId);

	@Modifying
	@Query(value = "update users set password=?1 where loginid=?2", nativeQuery = true)
	public void updateUser(String password, String loginid);

}

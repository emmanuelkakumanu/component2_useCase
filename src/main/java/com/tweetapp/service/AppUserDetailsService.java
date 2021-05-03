package com.tweetapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.getUserByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException("User does not exist!!");
		else
			return new AppUser(user);
	}
	
	@Transactional
	 public User findUser(String email) {
	 return userRepository.getUserByEmail(email);

	 }

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}

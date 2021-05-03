package com.tweetapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.UserDTO;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

@Service
public class UserService {
	public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public boolean register(UserDTO userDTO) {
		User user = new User();
		LOGGER.debug("userDto :" + userDTO);
		user.setLoginId(userDTO.getUserName());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmailId());
		user.setContactNumber(userDTO.getContactNo());
		User user1 = userRepository.getUserByEmail(userDTO.getEmailId());
		if (user1 == null) {
			String password = user.getPassword();
			user.setPassword(passwordEncoder().encode(password));
			userRepository.save(user);
			return true;
		} else {
			return false;

		}
	}

	@Transactional
	public boolean updatePassword(String loginid, String email, String password) {
		if (userRepository.getUserByLoginID(loginid).getEmail().equals(email)) {
			userRepository.updateUser(password, loginid);
			return true;
		}

		return false;

	}

	@Transactional
	public User getUser(String email) {
		return userRepository.getUserByEmail(email);
	}

	@Transactional
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}

package com.tweetapp.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.dto.UserDTO;
import com.tweetapp.model.User;
import com.tweetapp.service.AppUser;
import com.tweetapp.service.AppUserDetailsService;
import com.tweetapp.service.UserService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;

@Api
@RestController

@RequestMapping("/api/v1.0/tweets")
public class UserController {
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private AppUserDetailsService appUserDetailsService;

	@PostMapping("/register")
	public boolean register(@RequestBody @Validated UserDTO userDTO) {
		return userService.register(userDTO);

	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/login")
	public Map<String, String> login(@RequestHeader("Authorization") String authHeader) {
		Map<String, String> tokens = new HashMap<String, String>();
		String user = getUser(authHeader);
		String token = generateJwt(user);
		tokens.put("token", token);
		tokens.put("userName", user);
		AppUser appUser = (AppUser) appUserDetailsService.loadUserByUsername(user);
		// tokens.put("password", String.valueOf(appUser.getUser().getPassword()));
//		tokens.put("password", password);
		tokens.put("token", token);
		String loginid = userService.getUser(user).getLoginId();
		tokens.put("loginid", loginid);
		tokens.put("password", appUser.getPassword());
		LOGGER.debug("tokens : " + tokens);
		return tokens;
	}

	@GetMapping("/{loginid}/{email}/{password}/forgot")
	public boolean updatePassword(@PathVariable String loginid, @PathVariable String email,
			@PathVariable String password) {
		return userService.updatePassword(loginid, email, password);
	}

	@GetMapping("/users/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	private String getUser(String authHeader) {
		String encodedCredentials = authHeader.split(" ")[1];
		byte[] credentials = Base64.getDecoder().decode(encodedCredentials);
		String user = new String(credentials).split(":")[0];
		return user;
	}

	private String generateJwt(String user) {

		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);

		// Set the token issue time as current time
		builder.setIssuedAt(new Date());

		// Set the token expiry as 20 minutes from now
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");

		String token = builder.compact();

		return token;
	}

}

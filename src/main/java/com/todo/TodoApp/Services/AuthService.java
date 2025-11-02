package com.todo.TodoApp.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.todo.TodoApp.Models.User;
import com.todo.TodoApp.Repos.AuthRepo;

@Service
public class AuthService {

	@Autowired
	private AuthRepo repo;

	private static final String SECRET = "MySecretStringIs****";
	private static final Algorithm ALGO = Algorithm.HMAC256(SECRET);
	private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String userSignup(User u) {
		if (repo.findById(u.getUsername()).isPresent()) {
			return "User Already Registered";
		}
		// Hash the password before saving
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		repo.save(u);
		return "User created successfully";
	}

	public String userLogin(User u) {
		User res = repo.findById(u.getUsername()).orElse(null);
		if (res == null) {
			return "User not found";
		}

		// Validate password
		if (!passwordEncoder.matches(u.getPassword(), res.getPassword())) {
			return "Wrong password";
		}

		// Generate JWT token
		String token = JWT.create().withSubject(u.getUsername()).withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(ALGO);

		return token;
	}

	public List<User> getAll() {
		return repo.findAll();
	}
}

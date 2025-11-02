package com.todo.TodoApp.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.TodoApp.Models.User;
import com.todo.TodoApp.Services.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	@PostMapping("/signup")
	public ResponseEntity<?> signupMap(@RequestBody User u) {
		return ResponseEntity.ok(Map.of("message", service.userSignup(u)));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User u) {
		String token = service.userLogin(u);

		// If login failed
		if (!token.startsWith("ey")) {
			return ResponseEntity.badRequest().body(Map.of("message", token));
		}

		// Create JWT cookie
		ResponseCookie cookie = ResponseCookie.from("token", token).httpOnly(true) // not accessible by JavaScript
				.secure(false) // set true in production (HTTPS only)
				.path("/") // cookie sent on all endpoints
				.maxAge(10 * 60 * 60) // 10 hours
				.sameSite("Strict") // prevents CSRF
				.build();

		return ResponseEntity.ok().header("Set-Cookie", cookie.toString()).body(Map.of("message", "Login successful"));
	}

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(Map.of("data", service.getAll()));
	}
}

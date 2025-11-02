package com.todo.TodoApp.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.algorithms.Algorithm;
import com.todo.TodoApp.Models.User;
import com.todo.TodoApp.Services.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	private static final String secret = "MySecretStringIs****";
	private static final Algorithm algo = Algorithm.HMAC256(secret);
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupMap(@RequestBody User u) {
		return ResponseEntity.ok().body(Map.of("message",service.userSignup(u)));
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(Map.of("data",service.getAll()));
	}
}

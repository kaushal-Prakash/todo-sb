package com.todo.TodoApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.TodoApp.Models.User;
import com.todo.TodoApp.Repos.AuthRepo;

@Service
public class AuthService {
	@Autowired
	private AuthRepo repo;
	
	public String userSignup(User u) {
		if(!(repo.findById(u.getUsername()).isEmpty())) {
			return "User Already Registered";
		}
		repo.save(u);
		return "User created Successfully";
	}
	
	public List<User> getAll(){
		return repo.findAll();
	}
}

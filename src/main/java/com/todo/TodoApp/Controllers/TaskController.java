package com.todo.TodoApp.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
	
	public ResponseEntity<Map<String,String>> getTasks(){
		
	}
	
	@PostMapping
	public void addTask(String t) {
		
	}
}

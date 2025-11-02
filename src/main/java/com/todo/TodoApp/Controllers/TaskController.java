package com.todo.TodoApp.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.TodoApp.Models.Task;
import com.todo.TodoApp.Services.TaskService;

@RestController
@CrossOrigin	
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@GetMapping("/get-tasks")
	public ResponseEntity<Map<String, List<Task>>> getTasks(){
		return ResponseEntity.ok(Map.of("data",service.getAllTasks()));
	}
	
	@PostMapping("/add-task")
	public ResponseEntity<Map<String,String>> addTask(@RequestBody Task t) {
	    try {
	        service.addTask(t);
	        return ResponseEntity.ok(Map.of("message", "Task added successfully"));
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError()
	                .body(Map.of("message", "Failed to add Task"));
	    }
	}
}

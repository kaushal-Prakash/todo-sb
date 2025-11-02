package com.todo.TodoApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.TodoApp.Models.Task;
import com.todo.TodoApp.Repos.TaskRepo;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepo repo;
	
	public List<Task> getAllTasks(){
		return repo.findAll();
	}
	
	public void addTask(Task t) {
		repo.save(t);
	}
}

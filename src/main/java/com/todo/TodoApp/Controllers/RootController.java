package com.todo.TodoApp.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	@GetMapping("/")
	public String root() {
		return "Welcome to Todo App API..";
	}
}

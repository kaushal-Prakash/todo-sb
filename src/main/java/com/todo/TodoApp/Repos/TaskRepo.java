package com.todo.TodoApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.TodoApp.Models.Task;

public interface TaskRepo extends JpaRepository<Task, Integer> {

}

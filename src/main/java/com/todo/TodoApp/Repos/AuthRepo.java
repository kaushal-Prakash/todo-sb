package com.todo.TodoApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.TodoApp.Models.User;

public interface AuthRepo extends JpaRepository<User, String> {

}

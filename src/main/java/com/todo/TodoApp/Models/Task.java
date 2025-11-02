package com.todo.TodoApp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tId;
	private String text;
	private boolean isCompleted;
}

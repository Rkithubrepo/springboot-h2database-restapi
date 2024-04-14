package com.rkithub.todoappexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rkithub.todoappexample.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}

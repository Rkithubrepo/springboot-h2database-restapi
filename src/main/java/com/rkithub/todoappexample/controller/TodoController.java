package com.rkithub.todoappexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rkithub.todoappexample.model.Todo;
import com.rkithub.todoappexample.repository.TodoRepository;
import com.rkithubexample.exception.ResourceNotFoundException;

@RestController
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@GetMapping("/hello")
	public String getHello() {
		return "Testing";
		
		
	}
	
	 @GetMapping("/todos")
	    public List<Todo> getAllTodos() {
		 
			/*
			 * List<Todo> todos = new ArrayList<Todo>();
			 * 
			 * Todo todo1 = new Todo(1, "rakesh", "java", false); Todo todo2 = new Todo(2,
			 * "srileka", "html", true);
			 * 
			 * todos.add(todo1); todos.add(todo2); return todos;
			 */
	        
		 return todoRepository.findAll();
	    }
	 
	  @PostMapping("/todos/create")
	    public Todo createTodo(@RequestBody Todo todo) {
		  	  
	        return todoRepository.save(todo);
	    }
	  
	    @PutMapping("/todos/{id}")
	    public ResponseEntity<Todo> updateTodo(@PathVariable(value = "id") Integer todoId,
	                                           @RequestBody Todo todoDetails) {
	    Todo todo = todoRepository.findById(todoId).orElse(todoDetails);
	    
	    todo.setTask(todoDetails.getTask());
	    todo.setCompleted(todoDetails.isCompleted());
	    todo.setEmail(todoDetails.getEmail());

		final Todo updatedTodo = todoRepository.save(todo);
		return ResponseEntity.ok(updatedTodo);
}
		
		@DeleteMapping("todos/{id}")
		public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Integer todoId) {
			Todo todo = todoRepository.findById(todoId)
					.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + todoId));
		
			todoRepository.delete(todo);
			return ResponseEntity.ok().build();
		}
	    {
	    	
	    }
	

}

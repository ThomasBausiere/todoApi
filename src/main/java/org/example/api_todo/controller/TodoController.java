package org.example.api_todo.controller;


import org.example.api_todo.dto.TodoReceiveDto;
import org.example.api_todo.dto.TodoResponseDto;
import org.example.api_todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAll(){
        return ResponseEntity.ok(todoService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> get (@PathVariable UUID id){
        return ResponseEntity.ok(todoService.get(id));
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> create(@RequestBody TodoReceiveDto todoReceiveDto){
g        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(todoReceiveDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> update(@PathVariable UUID id, @RequestBody TodoReceiveDto todoReceiveDto){
        return ResponseEntity.ok(todoService.update(id, todoReceiveDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        todoService.delete(id);
        return ResponseEntity.ok(String.format("Todo at id: %s is deleted", id));
    }


}

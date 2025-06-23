package org.example.api_todo.service;


import org.example.api_todo.dto.TodoReceiveDto;
import org.example.api_todo.dto.TodoResponseDto;
import org.example.api_todo.entity.Todo;
import org.example.api_todo.exception.NotFoundException;
import org.example.api_todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //CREATE

    public TodoResponseDto create (TodoReceiveDto todoReceiveDto){
        return todoRepository.save(todoReceiveDto.dtoToEntity()).entityToDto();
    }

    //READ 1
    public TodoResponseDto get(UUID id){
        return todoRepository.findById(id).orElseThrow(NotFoundException::new).entityToDto();
    }
    //READ ALL
    public List<TodoResponseDto> get(){
        return todoRepository.findAll().stream().map(Todo::entityToDto).toList();
    }
    //UPDATE

    public TodoResponseDto update(UUID id , TodoReceiveDto todoReceiveDto){
           Todo todoFound =todoRepository.findById(id).orElseThrow(NotFoundException::new);
            Todo todoGet = todoReceiveDto.dtoToEntity();
            todoFound.setTitle(todoGet.getTitle());
            todoFound.setDescription(todoGet.getDescription());
            todoFound.setCreatedDate(todoGet.getCreatedDate());
            todoFound.setValidate(todoGet.isValidate());

            return todoRepository.save(todoFound).entityToDto();
    }

    //DELETE

    public void delete(UUID id){
        todoRepository.deleteById(id);
    }
}

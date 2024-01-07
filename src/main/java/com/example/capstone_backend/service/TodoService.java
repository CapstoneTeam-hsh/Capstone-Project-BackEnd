package com.example.capstone_backend.service;

import com.example.capstone_backend.domain.Todo;
import com.example.capstone_backend.domain.User;
import com.example.capstone_backend.dto.Request.TodoRequestDto;
import com.example.capstone_backend.dto.Response.TodoResponseDto;
import com.example.capstone_backend.repository.TodoRepository;
import com.example.capstone_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;


    public TodoResponseDto getTodo(Long todoId){

        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new NoSuchElementException("해당 투두가 존재하지 않습니다."));

        TodoResponseDto todoResponseDto = TodoResponseDto.convertEntityToDto(todo);

        return todoResponseDto;
    }

    public TodoResponseDto saveTodo(Long userId, TodoRequestDto todoRequestDto){
        User user = userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("해당 유저가 존재하지 않습니다."));

        Todo todo = TodoRequestDto.convertDtoToEntity(todoRequestDto,user);
        todoRepository.save(todo);

        TodoResponseDto todoResponseDto = TodoResponseDto.convertEntityToDto(todo);

        return todoResponseDto;
    }

    public TodoResponseDto updateTodo(Long todoId, TodoRequestDto todoRequestDto){
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new NoSuchElementException("해당 투두가 존재하지 않습니다."));

        todo.updateTodo(todoRequestDto.getTitle(),todoRequestDto.getContents());

        todoRepository.save(todo);
        TodoResponseDto todoResponseDto = TodoResponseDto.convertEntityToDto(todo);

        return todoResponseDto;
    }

    public void deleteTodo(Long todoId){
        todoRepository.deleteById(todoId);
    }

    public TodoResponseDto checkCompleted(Long todoId,Boolean completed){
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new NoSuchElementException("해당 투두가 존재하지 않습니다."));
        todo.checkCompleted();

        todoRepository.save(todo);

        TodoResponseDto todoResponseDto = TodoResponseDto.convertEntityToDto(todo);

        return todoResponseDto;
    }

}

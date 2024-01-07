package com.example.capstone_backend.service;

import com.example.capstone_backend.domain.Todo;
import com.example.capstone_backend.domain.User;
import com.example.capstone_backend.dto.Request.UserRequestDto;
import com.example.capstone_backend.dto.Response.TodoResponseDto;
import com.example.capstone_backend.dto.Response.UserResponseDto;
import com.example.capstone_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 id의 유저가 존재하지 않습니다."));

        UserResponseDto userResponseDto = UserResponseDto.convertEntityToDto(user);

        return userResponseDto;
    }

    public UserResponseDto savedUser(UserRequestDto userRequestDto){
        User user = UserRequestDto.convertDtoToEntity(userRequestDto);
        userRepository.save(user);
        UserResponseDto userResponseDto = UserResponseDto.convertEntityToDto(user);

        return userResponseDto;
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto){
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 id의 유저가 존재하지 않습니다."));
        user.updateUserInfo(userRequestDto.getName(),userRequestDto.getEmail());

        UserResponseDto userResponseDto = UserResponseDto.convertEntityToDto(user);

        return userResponseDto;
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 id의 유저가 존재하지 않습니다."));
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> getTodosByUserId(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("해당 id의 유저가 존재하지 않습니다."));

        List<Todo> todos = user.getTodoList();

        return todos.stream().map(TodoResponseDto::convertEntityToDto).collect(Collectors.toList());
    }


}

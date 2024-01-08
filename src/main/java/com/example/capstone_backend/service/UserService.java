package com.example.capstone_backend.service;

import com.example.capstone_backend.domain.Team;
import com.example.capstone_backend.domain.Todo;
import com.example.capstone_backend.domain.User;
import com.example.capstone_backend.domain.UserTeam;
import com.example.capstone_backend.dto.Request.UserRequestDto;
import com.example.capstone_backend.dto.Response.TeamResponseDto;
import com.example.capstone_backend.dto.Response.TodoResponseDto;
import com.example.capstone_backend.dto.Response.UserResponseDto;
import com.example.capstone_backend.repository.UserTeamRepository;
import com.example.capstone_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserTeamRepository userTeamRepository;

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

    // 유저의 로그인 아이디로 UserGroup 테이블을 통해 해당 유저가 속한 Group을 출력
    @Transactional(readOnly = true)
    public List<TeamResponseDto> getTeamByUid(String uid){

        User user = userRepository.getByUid(uid).orElseThrow(()->new NoSuchElementException("해당 유저가 존재하지 않습니다."));
        List<UserTeam> userTeams = user.getUserToGroups();

        List<TeamResponseDto> teamResponseDtos = new ArrayList<>();

        for (UserTeam userTeam : userTeams){
            Team team = userTeam.getTeam();
            TeamResponseDto item = TeamResponseDto.convertTeamToDto(team);
            teamResponseDtos.add(item);
        }

        return teamResponseDtos;
    }
}

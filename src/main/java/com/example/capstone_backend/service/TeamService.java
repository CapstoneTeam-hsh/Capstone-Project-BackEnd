package com.example.capstone_backend.service;

import com.example.capstone_backend.domain.Team;
import com.example.capstone_backend.domain.TeamTodo;
import com.example.capstone_backend.domain.User;
import com.example.capstone_backend.domain.UserTeam;
import com.example.capstone_backend.dto.Request.TeamRequestDto;
import com.example.capstone_backend.dto.Response.TeamResponseDto;
import com.example.capstone_backend.dto.Response.TeamTodoResponseDto;
import com.example.capstone_backend.dto.Response.UserResponseDto;
import com.example.capstone_backend.repository.TeamRepository;
import com.example.capstone_backend.repository.UserRepository;
import com.example.capstone_backend.repository.UserTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final UserTeamRepository userTeamRepository;

    public TeamResponseDto getTeam(Long teamId){
        Team team = teamRepository.findById(teamId).orElseThrow(()-> new NoSuchElementException("해당 그룹이 존재하지 않습니다."));

        TeamResponseDto teamResponseDto = TeamResponseDto.convertTeamToDto(team);

        return teamResponseDto;
    }

    @Transactional
    public TeamResponseDto saveTeam(TeamRequestDto teamRequestDto, String uid){
        User user = userRepository.getByUid(uid).orElseThrow(()->new NoSuchElementException("해당 유저가 존재하지 않습니다."));

        Team team = TeamRequestDto.convertGroupDtoToGroup(teamRequestDto);

        UserTeam userTeam = new UserTeam();
        userTeam.setTeam(team);
        userTeam.setUser(user);

        userTeamRepository.save(userTeam);
        teamRepository.save(team);

        TeamResponseDto teamResponseDto = TeamResponseDto.convertTeamToDto(team);

        return teamResponseDto;
    }

    public void deleteTeam(Long id){
        teamRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getUserByTeam(Long teamId){
        Team team = teamRepository.findById(teamId).orElseThrow(()-> new NoSuchElementException("해당 그룹이 존재하지 않습니다."));
        List<UserTeam> userTeams = team.getUserToTeam();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (UserTeam userTeam : userTeams){
            User user = userTeam.getUser();
            UserResponseDto item = UserResponseDto.convertEntityToDto(user);
            userResponseDtos.add(item);
        }
        return userResponseDtos;
    }

    @Transactional(readOnly = true)
    public List<TeamTodoResponseDto> getTeamTodoByTeam(Long teamId){
        Team team = teamRepository.findById(teamId).orElseThrow(()->new NoSuchElementException("해당 그룹이 존재하지 않습니다."));

        List<TeamTodo> teamTodos = team.getTeamTodos();

        return teamTodos.stream().map(TeamTodoResponseDto::convertEntityToDto).collect(Collectors.toList());
    }


    @Transactional
    public List<UserResponseDto> addUserToTeam(Long teamId, String uid){
        User user = userRepository.getByUid(uid).orElseThrow(()->new RuntimeException("해당 유저가 존재하지 않습니다."));
        Team team = teamRepository.findById(teamId).orElseThrow(()->new NoSuchElementException("해당 그룹이 존재하지 않습니다."));

        UserTeam userTeam = new UserTeam();
        userTeam.setTeam(team);
        userTeam.setUser(user);
        userTeamRepository.save(userTeam);

        List<UserTeam> userTeams = team.getUserToTeam();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (UserTeam userTeam1 : userTeams){
            User user1 = userTeam1.getUser();
            UserResponseDto userResponseDto = UserResponseDto.convertEntityToDto(user1);
            userResponseDtos.add(userResponseDto);
        }

        return userResponseDtos;
    }
}

package com.example.capstone_backend.service;

import com.example.capstone_backend.domain.Team;
import com.example.capstone_backend.domain.TeamTodo;
import com.example.capstone_backend.dto.Request.TeamTodoRequestDto;
import com.example.capstone_backend.dto.Response.TeamTodoResponseDto;
import com.example.capstone_backend.repository.TeamRepository;
import com.example.capstone_backend.repository.TeamTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeamTodoService {

    private final TeamTodoRepository teamTodoRepository;
    private final TeamRepository teamRepository;

    public TeamTodoResponseDto getTeamTodo(Long teamTodoId){

        TeamTodo teamTodo = teamTodoRepository.findById(teamTodoId).orElseThrow(()->new NoSuchElementException("해당 그룹 투두가 존재하지 않습니다."));

        TeamTodoResponseDto teamTodoResponseDto = TeamTodoResponseDto.convertEntityToDto(teamTodo);

        return teamTodoResponseDto;
    }

    public TeamTodoResponseDto saveTeamTodo(Long teamId, TeamTodoRequestDto teamTodoRequestDto){
        Team team = teamRepository.findById(teamId).orElseThrow(()-> new NoSuchElementException("해당 그룹이 존재하지 않습니다."));

        TeamTodo teamTodo = TeamTodoRequestDto.convertDtoToEntity(teamTodoRequestDto,team);
        teamTodoRepository.save(teamTodo);

        TeamTodoResponseDto teamTodoResponseDto = TeamTodoResponseDto.convertEntityToDto(teamTodo);

        return teamTodoResponseDto;
    }

    public TeamTodoResponseDto updateTeamTodo(Long teamTodoId, TeamTodoRequestDto teamTodoRequestDto){
        TeamTodo teamTodo = teamTodoRepository.findById(teamTodoId).orElseThrow(()->new NoSuchElementException("해당 그룹 투두가 존재하지 않습니다."));

        teamTodo.updateTeamTodo(teamTodoRequestDto.getTitle(), teamTodoRequestDto.getContents());

        teamTodoRepository.save(teamTodo);
        TeamTodoResponseDto teamTodoResponseDto = TeamTodoResponseDto.convertEntityToDto(teamTodo);

        return teamTodoResponseDto;
    }

    public void deleteTeamTodo(Long teamTodoId){
        teamTodoRepository.deleteById(teamTodoId);
    }


    public TeamTodoResponseDto checkCompleted(Long teamTodoId, Boolean completed){
        TeamTodo teamTodo = teamTodoRepository.findById(teamTodoId).orElseThrow(()->new NoSuchElementException("해당 그룹 투두가 존재하지 않습니다."));
        teamTodo.checkCompleted();

        teamTodoRepository.save(teamTodo);

        TeamTodoResponseDto teamTodoResponseDto = TeamTodoResponseDto.convertEntityToDto(teamTodo);

        return teamTodoResponseDto;
    }


}

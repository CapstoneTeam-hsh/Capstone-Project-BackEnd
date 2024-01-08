package com.example.capstone_backend.controller;

import com.example.capstone_backend.dto.Request.TeamTodoRequestDto;
import com.example.capstone_backend.dto.Response.TeamTodoResponseDto;
import com.example.capstone_backend.service.TeamTodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name ="그룹 투두 정보", description = "그룹 할일 생성,그룹 할일 성공여부 체크,그룹 할일 조회,그룹 할일 수정,그룹 할일 삭제")
@Slf4j
@RestController
@RequestMapping("/groupTodos")
@RequiredArgsConstructor
public class TeamTodoController {

    private final TeamTodoService teamTodoService;

    @Operation(summary = "그룹 투두 조회",description = "그룹 투두 번호로 투두를 조회합니다.")
    @Parameter(name="teamTodoId",description = "그룹 투두 번호")
    @GetMapping("/{teamTodoId}")
    public ResponseEntity<TeamTodoResponseDto> getTeamTodo(@PathVariable(name="teamTodoId") Long teamTodoId){
        log.info("[getTeamTodo] 그룹 투두 번호로 그룹 투두를 조회합니다. groupTodoId : {}",teamTodoId);
        TeamTodoResponseDto teamTodoResponseDto = teamTodoService.getTeamTodo(teamTodoId);

        return ResponseEntity.status(HttpStatus.OK).body(teamTodoResponseDto);
    }

    @Operation(summary = "그룹 투두 생성",description = "해당 그룹의 투두를 셍성합니다.")
    @Parameter(name="teamId",description = "그룹 번호")
    @PostMapping("/{teamId}")
    public ResponseEntity<TeamTodoResponseDto> createTeamTodo(@PathVariable(name="teamId") Long teamId, @RequestBody TeamTodoRequestDto teamTodoRequestDto){
        log.info("[createTeamTodo] 현재 그룹으로 그룹 할 일을 생성합니다. groupId : {}",teamId);

        TeamTodoResponseDto teamTodoResponseDto = teamTodoService.saveTeamTodo(teamId, teamTodoRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(teamTodoResponseDto);
    }

    @Operation(summary = "그룹 투두 수정",description = "그룹 투두 번호로 투두를 수정합니다.")
    @Parameter(name="teamTodoId",description = "그룹 투두 번호")
    @PutMapping("/{teamTodoId}")
    public ResponseEntity<TeamTodoResponseDto> changeTeamTodo(@PathVariable(name="teamTodoId") Long teamTodoId, @RequestBody TeamTodoRequestDto teamTodoRequestDto){
        log.info("[changeTeamTodo] 그룹 할 일을 수정합니다.");

        TeamTodoResponseDto teamTodoResponseDto = teamTodoService.updateTeamTodo(teamTodoId, teamTodoRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(teamTodoResponseDto);
    }

    @Operation(summary = "그룹 투두 삭제",description = "그룹 투두 번호로 투두를 삭제합니다.")
    @Parameter(name="teamTodoId",description = "그룹 투두 번호")
    @DeleteMapping("/{teamTodoId}")
    public ResponseEntity<Void> deleteTeamTodo(@PathVariable(name="teamTodoId") Long teamTodoId){
        log.info("[deleteTeamTodo] 그룹 투두를 삭제합니다. groupTodoId: {}",teamTodoId);
        teamTodoService.deleteTeamTodo(teamTodoId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "그룹 투두 성공여부 체크",description = "그룹 투두 번호로 투두의 성공여부를 체크합니다.")
    @Parameter(name="teamTodoId",description = "그룹 투두 번호")
    @PutMapping("/check")
    public ResponseEntity<TeamTodoResponseDto> checkCompletedTeamTodo(@RequestParam(name = "teamTodoId") Long teamTodoId, @RequestParam(name ="completed") Boolean completed){
        log.info("[checkCompletedTodo] 그룹 할일의 성공 여부를 체크합니다. teamTodoId: {}",teamTodoId);
        TeamTodoResponseDto teamTodoResponseDto = teamTodoService.checkCompleted(teamTodoId,completed);

        return ResponseEntity.status(HttpStatus.OK).body(teamTodoResponseDto);
    }
}

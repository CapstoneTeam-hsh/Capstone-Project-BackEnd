package com.example.capstone_backend.controller;

import com.example.capstone_backend.dto.Request.TeamRequestDto;
import com.example.capstone_backend.dto.Response.TeamResponseDto;
import com.example.capstone_backend.dto.Response.TeamTodoResponseDto;
import com.example.capstone_backend.dto.Response.UserResponseDto;
import com.example.capstone_backend.service.TeamService;
import com.example.capstone_backend.service.TeamTodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name ="그룹 정보", description = "그룹 생성,그룹 조회,그룹 수정,그룹 삭제, 그룹 할일 조회")
@Slf4j
@RequestMapping("/groups")
@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @Operation(summary = "그룹 조회", description = "그룹 번호로 그룹을 조회합니다.")
    @Parameter(name = "teamId", description = "그룹 번호")
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDto> getTeam(@PathVariable(name = "teamId") Long teamId) {
        log.info("[getGroup] groupId로 유저 정보를 가져옵니다. groupId : {}", teamId);

        TeamResponseDto teamResponseDto = teamService.getTeam(teamId);

        return ResponseEntity.status(HttpStatus.OK).body(teamResponseDto);
    }

    @Operation(summary = "그룹 생성", description = "그룹을 생성합니다.")
    @PostMapping("/create/{uid}")
    public ResponseEntity<TeamResponseDto> createTeam(@PathVariable(name = "uid") String uid, @RequestBody TeamRequestDto teamRequestDto) {
        log.info("[createUser] 그룹을 생성합니다. 유저 로그인 아이디 : {}", uid);
        TeamResponseDto teamResponseDto = teamService.saveTeam(teamRequestDto, uid);

        return ResponseEntity.status(HttpStatus.CREATED).body(teamResponseDto);
    }

    @Operation(summary = "그룹 삭제",description = "그룹을 삭제합니다.")
    @DeleteMapping("/delete/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable(name="teamId") Long teamId){
        log.info("[deleteTeam] 그룹을 삭제합니다. 그룹 아이디 : {}",teamId);
        teamService.deleteTeam(teamId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "그룹에 속한 유저 조회", description = "그룹에 속한 유저들을 조회합니다.")
    @GetMapping("/users/{teamId}")
    public ResponseEntity<List<UserResponseDto>> getUserByTeam(@PathVariable(name = "teamId")Long teamId){
        log.info("[getUserByGroup] 해당 그룹에 속한 유저들을 조회합니다. 그룹 아이디 : {}",teamId);
        List<UserResponseDto> userResponseDtos = teamService.getUserByTeam(teamId);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtos);
    }

    @Operation(summary = "그룹에 해당하는 투두 조회",description ="그룹에서 생성한 투두들을 조회합니다." )
    @GetMapping("/teamTodos/{groupId}")
    public ResponseEntity<List<TeamTodoResponseDto>> getTeamTodoByTeam(@PathVariable(name = "groupId")Long groupId){
        log.info("[getTeamTodoByTeam] 해당 그룹에서 생성한 투두들을 조회합니다. groupId: {}",groupId);
        List<TeamTodoResponseDto> teamTodoResponseDtos = teamService.getTeamTodoByTeam(groupId);

        return ResponseEntity.status(HttpStatus.OK).body(teamTodoResponseDtos);
    }

    @Operation(summary = "그룹에 유저 추가",description = "그룹에 유저를 추가한다.")
    @PostMapping("/addition")
    public ResponseEntity<List<UserResponseDto>> addUserToTeam(@RequestParam(name = "teamId")Long teamId,@RequestParam(name = "uid")String uid){
        log.info("[addUserToTeam] 그룹에 유저를 추가합니다. 유저 로그인 아이디: {}", uid);
        List<UserResponseDto> userResponseDtos = teamService.addUserToTeam(teamId,uid);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtos);
    }
}
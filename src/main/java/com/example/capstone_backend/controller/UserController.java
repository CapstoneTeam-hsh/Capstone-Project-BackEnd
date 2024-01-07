package com.example.capstone_backend.controller;

import com.example.capstone_backend.dto.Request.UserRequestDto;
import com.example.capstone_backend.dto.Response.TodoResponseDto;
import com.example.capstone_backend.dto.Response.UserResponseDto;
import com.example.capstone_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name ="유저 정보", description = "유저 회원가입,유저 조회,유저 수정,유저 삭제, 유저 할일 조회")
@Slf4j
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @Operation(summary = "유저 조회",description = "유저 번호로 유저를 조회합니다.")
    @Parameter(name="userId",description = "유저 번호")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable(name="userId") Long userId){
        log.info("[getUser] userId로 유저 정보를 가져옵니다. userId : {}",userId);
        UserResponseDto userResponseDto = userService.getUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @Operation(summary = "유저 생성",description = "아이디, 비밀번호, 이름, 이메일을 입력해주세요")
    @PostMapping()
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        log.info("[createUser] 유저를 생성합니다. 유저 로그인 아이디 : {}",userRequestDto.getUid());
        UserResponseDto userResponseDto = userService.savedUser(userRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @Operation(summary = "유저 수정",description = "유저 번호로 해당 유저의 정보를 수정합니다.")
    @Parameter(name="userId",description = "유저 번호")
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponseDto> changeUser(@PathVariable(name="userId") Long userId, @RequestBody UserRequestDto userRequestDto){
        log.info("[changeUser] 유저의 정보를 수정합니다. userId : {}",userId);
        UserResponseDto userResponseDto = userService.updateUser(userId,userRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @Operation(summary = "유저 삭제",description = "유저 번호로 유저를 삭제합니다.")
    @Parameter(name="userId",description = "유저 번호")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name="userId") Long userId){
        log.info("[deleteUser] 유저를 삭제합니다. userId : {}",userId);
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "유저 투두 조회",description = "유저 번호로 유저가 생성한 투두를 조회합니다.")
    @Parameter(name="userId",description = "유저 번호")
    @GetMapping("/todos/{userId}")
    public ResponseEntity<List<TodoResponseDto>> getTodoList(@PathVariable(name="userId") Long userId){
        log.info("[getTodoList] 해당 유저의 투두들을 불러옵니다. userId : {}",userId);
        List<TodoResponseDto> userTodoList = userService.getTodosByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userTodoList);
    }






}

package com.example.capstone_backend.controller;

import com.example.capstone_backend.dto.Request.TodoRequestDto;
import com.example.capstone_backend.dto.Response.TodoResponseDto;
import com.example.capstone_backend.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name ="투두 정보", description = "할일 생성,할일 성공여부 체크,할일 조회,할일 수정,할일 삭제")
@Slf4j
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "투두 조회",description = "투두 번호로 투두를 조회합니다.")
    @Parameter(name="todoId",description = "투두 번호")
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> getTodo(@PathVariable(name="todoId") Long todoId){
        log.info("[getTodo] 투두 번호로 투두를 조회합니다. todoId : {}",todoId);
        TodoResponseDto todoResponseDto = todoService.getTodo(todoId);

        return ResponseEntity.status(HttpStatus.OK).body(todoResponseDto);
    }

    @Operation(summary = "투두 생성",description = "해당 유저의 투두를 셍성합니다.")
    @Parameter(name="userId",description = "유저 번호")
    @PostMapping("/{userId}")
    public ResponseEntity<TodoResponseDto> createTodo(@PathVariable(name="userId") Long userId, @RequestBody TodoRequestDto todoRequestDto){
        log.info("[createTodo] 현재 사용자의 계정으로 할일을 생성합니다. userId : {}",userId);

        TodoResponseDto todoResponseDto = todoService.saveTodo(userId,todoRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(todoResponseDto);
    }

    @Operation(summary = "투두 수정",description = "투두 번호로 투두를 수정합니다.")
    @Parameter(name="todoId",description = "투두 번호")
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> changeTodo(@PathVariable(name="todoId") Long todoId, @RequestBody TodoRequestDto todoRequestDto){
        log.info("[changeTodo] 할 일을 수정합니다.");

        TodoResponseDto todoResponseDto = todoService.updateTodo(todoId,todoRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(todoResponseDto);
    }

    @Operation(summary = "투두 삭제",description = "투두 번호로 투두를 삭제합니다.")
    @Parameter(name="todoId",description = "투두 번호")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable(name="todoId") Long todoId){
        log.info("[deleteTodo] 투두를 삭제합니다. todoId: {}",todoId);
        todoService.deleteTodo(todoId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "투두 성공여부 체크",description = "투두 번호로 투두의 성공여부를 체크합니다.")
    @Parameter(name="todoId",description = "투두 번호")
    @PutMapping("/check")
    public ResponseEntity<TodoResponseDto> checkCompletedTodo(@RequestParam(name = "todoId") Long todoId, @RequestParam(name ="completed") Boolean completed){
        log.info("[checkCompletedTodo] 할일의 성공 여부를 체크합니다. todoId: {}",todoId);
        TodoResponseDto todoResponseDto = todoService.checkCompleted(todoId,completed);

        return ResponseEntity.status(HttpStatus.OK).body(todoResponseDto);
    }
}

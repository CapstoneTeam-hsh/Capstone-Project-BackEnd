package com.example.capstone_backend.dto.Response;

import com.example.capstone_backend.domain.TeamTodo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamTodoResponseDto {

    private Long id;
    private String title;
    private String contents;
    private Boolean completed;

    @Builder
    public TeamTodoResponseDto(Long id, String title, String contents, Boolean completed){
        this.id = id;
        this.title=title;
        this.contents = contents;
        this.completed=completed;
    }

    public static TeamTodoResponseDto convertEntityToDto(TeamTodo teamTodo)
    {
        return TeamTodoResponseDto.builder()
                .id(teamTodo.getId())
                .title(teamTodo.getTitle())
                .contents(teamTodo.getContents())
                .completed(teamTodo.getCompleted())
                .build();
    }

}

package com.example.capstone_backend.dto.Response;

import com.example.capstone_backend.domain.Todo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoResponseDto {
    private Long id;
    private String title;
    private String contents;
    private Boolean completed;


    @Builder
    public TodoResponseDto(Long id, String title, String contents, Boolean completed){
        this.id = id;
        this.title=title;
        this.contents = contents;
        this.completed=completed;
    }

    public static TodoResponseDto convertEntityToDto(Todo todo)
    {
        return TodoResponseDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .contents(todo.getContents())
                .completed(todo.getCompleted())
                .build();
    }


}

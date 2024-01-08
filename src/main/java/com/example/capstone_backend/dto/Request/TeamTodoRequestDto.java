package com.example.capstone_backend.dto.Request;

import com.example.capstone_backend.domain.Team;
import com.example.capstone_backend.domain.TeamTodo;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamTodoRequestDto {

    @NotBlank(message = "그룹 할 일 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "그룹 할 일 내용을 입력해주세요.")
    private String contents;

    @Builder
    public TeamTodoRequestDto(String title, String contents)
    {
        this.title= title;
        this.contents = contents;
    }

    public static TeamTodo convertDtoToEntity(TeamTodoRequestDto teamTodoRequestDto, Team team){
        return TeamTodo.builder()
                .title(teamTodoRequestDto.getTitle())
                .contents(teamTodoRequestDto.getContents())
                .team(team)
                .build();
    }

}

package com.example.capstone_backend.dto.Request;

import com.example.capstone_backend.domain.Todo;
import com.example.capstone_backend.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoRequestDto {

    @NotBlank(message = "할 일 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "할 일 내용을 입력해주세요.")
    private String contents;

    @Builder
    public TodoRequestDto(String title, String contents)
    {
        this.title= title;
        this.contents = contents;
    }

    public static Todo convertDtoToEntity(TodoRequestDto todoRequestDto, User user){
        return Todo.builder()
                .title(todoRequestDto.getTitle())
                .contents(todoRequestDto.getContents())
                .user(user)
                .build();
    }
}

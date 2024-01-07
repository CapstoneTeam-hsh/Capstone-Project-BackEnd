package com.example.capstone_backend.dto.Request;

import com.example.capstone_backend.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;
    
    @NotBlank(message = "유저아이디를 입력해주세요")
    private String uid;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @Builder
    public UserRequestDto(String name,String email,String uid, String password)
    {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.password = password;
    }

    public static User convertDtoToEntity(UserRequestDto userRequestDto)
    {
        return User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .uid(userRequestDto.getUid())
                .password(userRequestDto.getPassword())
                .build();
    }



}

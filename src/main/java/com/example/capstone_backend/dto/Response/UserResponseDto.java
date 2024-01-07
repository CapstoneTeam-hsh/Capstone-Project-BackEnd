package com.example.capstone_backend.dto.Response;

import com.example.capstone_backend.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String uid;
    private String name;
    private String email;

    @Builder
    public UserResponseDto(Long id, String uid,String name, String email)
    {
        this.id=id;
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public static UserResponseDto convertEntityToDto(User user)
    {
        return UserResponseDto.builder()
                .id(user.getId())
                .uid(user.getUid())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

}

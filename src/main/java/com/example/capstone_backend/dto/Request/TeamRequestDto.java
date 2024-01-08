package com.example.capstone_backend.dto.Request;

import com.example.capstone_backend.domain.Team;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamRequestDto {

    @NotBlank(message = "그룹 이름을 입력해주세요")
    private String teamName;
    @Builder
    public TeamRequestDto(String teamName){
        this.teamName =teamName;
    }

    public static Team convertGroupDtoToGroup(TeamRequestDto teamRequestDto){
        return Team.builder()
                .teamName(teamRequestDto.getTeamName())
                .build();
    }



}

package com.example.capstone_backend.dto.Response;

import com.example.capstone_backend.domain.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamResponseDto {
    private Long id;
    private String groupName;

    @Builder
    public TeamResponseDto(Long id, String groupName){
        this.id = id;
        this.groupName = groupName;
    }

    public static TeamResponseDto convertTeamToDto(Team team)
    {
        return TeamResponseDto.builder()
                .id(team.getId())
                .groupName(team.getTeamName())
                .build();
    }

}

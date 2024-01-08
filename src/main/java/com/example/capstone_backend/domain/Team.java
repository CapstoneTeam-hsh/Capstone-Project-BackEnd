package com.example.capstone_backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Team_tb")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(nullable = false)
    private String teamName;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<TeamTodo> TeamTodos = new ArrayList<>();

    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UserTeam> userToTeam = new ArrayList<>();

    @Builder
    public Team(String teamName)
    {
        this.teamName =teamName;
    }

}

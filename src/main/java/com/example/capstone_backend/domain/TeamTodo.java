package com.example.capstone_backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "teamTodo_tb")
public class TeamTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamTodo_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Team", nullable = false)
    private Team team;

    @Builder
    public TeamTodo(String title,String contents, Boolean completed, Team team){
        this.title = title;
        this.contents =contents;
        this.completed = false;
        this.team = team;
    }

    public void updateTeamTodo(String title,String contents){
        this.title = title;
        this.contents = contents;
    }

    public void checkCompleted(){this.completed = !completed;}
}

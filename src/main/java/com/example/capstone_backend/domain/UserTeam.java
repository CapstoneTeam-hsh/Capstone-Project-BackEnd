package com.example.capstone_backend.domain;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "UserGroup_tb")
public class UserTeam {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserGroup_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

}

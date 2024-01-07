package com.example.capstone_backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "todo_tb")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Builder
    public Todo(String title, String contents,User user){
        this.title = title;
        this.contents =contents;
        this.completed = false;
        this.user = user;
    }

    public void checkCompleted(){this.completed = !completed;}

    public void updateTodo(String title,String contents){
        this.title = title;
        this.contents = contents;
    }

}

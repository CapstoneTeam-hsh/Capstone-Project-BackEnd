package com.example.capstone_backend.repository;

import com.example.capstone_backend.domain.TeamTodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamTodoRepository extends JpaRepository<TeamTodo,Long> {
}

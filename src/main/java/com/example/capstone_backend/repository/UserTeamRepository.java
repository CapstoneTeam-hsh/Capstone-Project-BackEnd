package com.example.capstone_backend.repository;

import com.example.capstone_backend.domain.User;
import com.example.capstone_backend.domain.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTeamRepository extends JpaRepository<UserTeam,Long> {
    Optional<UserTeam> getByUser(User user);
}

package com.example.dev.repo;

import com.example.dev.entity.Role;
import com.example.dev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    boolean existsByRole(Role role);
}

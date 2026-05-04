package com.example.dev.service;

import com.example.dev.controller.dto.LoginRequest;
import com.example.dev.controller.dto.RegisterRequest;
import com.example.dev.entity.Role;
import com.example.dev.entity.User;
import com.example.dev.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest req) {
        if (userRepository.findByLogin(req.login()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setLogin(req.login());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    public String login(LoginRequest req) {
        User user = userRepository.findByLogin(req.login())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getLogin(), user.getRole().name());
    }
}

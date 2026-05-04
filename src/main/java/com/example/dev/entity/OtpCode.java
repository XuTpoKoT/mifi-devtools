package com.example.dev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_codes")
@Getter
@Setter
public class OtpCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Long operationId;
    @Enumerated(EnumType.STRING)
    private OtpStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    @ManyToOne
    private User user;
}

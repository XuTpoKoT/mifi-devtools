package com.example.dev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "otp_config")
@Getter
@Setter
public class OtpConfig {
    @Id
    private Long id = 1L;
    private Integer codeLength;
    private Integer ttlSeconds;
}
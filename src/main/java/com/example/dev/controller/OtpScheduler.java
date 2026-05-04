package com.example.dev.controller;

import com.example.dev.entity.OtpCode;
import com.example.dev.entity.OtpStatus;
import com.example.dev.repo.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OtpScheduler {
    private final OtpRepository repo;

    @Scheduled(fixedRate = 60000)
    public void expireOtps() {
        List<OtpCode> active = repo.findByStatus(OtpStatus.ACTIVE);

        for (OtpCode otp : active) {
            if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
                otp.setStatus(OtpStatus.EXPIRED);
            }
        }

        repo.saveAll(active);
    }
}

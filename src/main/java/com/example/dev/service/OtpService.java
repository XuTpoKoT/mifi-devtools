package com.example.dev.service;

import com.example.dev.entity.OtpCode;
import com.example.dev.entity.OtpConfig;
import com.example.dev.entity.OtpStatus;
import com.example.dev.entity.User;
import com.example.dev.repo.OtpConfigRepository;
import com.example.dev.repo.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;
    private final OtpConfigRepository configRepository;

    public String generateOtp(User user, Long operationId) {

        OtpConfig config = configRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("OtpConfig not found"));

        String code = generateCode(config.getCodeLength());

        OtpCode otp = new OtpCode();
        otp.setCode(code);
        otp.setUser(user);
        otp.setOperationId(operationId);
        otp.setStatus(OtpStatus.ACTIVE);
        otp.setCreatedAt(LocalDateTime.now());
        otp.setExpiresAt(LocalDateTime.now().plusSeconds(config.getTtlSeconds()));

        otpRepository.save(otp);

        return code;
    }

    public boolean validateOtp(User user, String code, Long operationId) {
        OtpCode otp = otpRepository
                .findTopByUserAndOperationIdAndCodeOrderByCreatedAtDesc(user, operationId, code)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (otp.getStatus() != OtpStatus.ACTIVE) {
            return false;
        }

        if (otp.getExpiresAt().isBefore(LocalDateTime.now())) {
            otp.setStatus(OtpStatus.EXPIRED);
            otpRepository.save(otp);
            return false;
        }

        otp.setStatus(OtpStatus.USED);
        otpRepository.save(otp);

        return true;
    }

    private String generateCode(int length) {
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length) - 1;
        return String.valueOf(ThreadLocalRandom.current().nextInt(min, max + 1));
    }
}

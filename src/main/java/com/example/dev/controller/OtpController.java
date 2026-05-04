package com.example.dev.controller;

import com.example.dev.controller.dto.GenerateOtpRequest;
import com.example.dev.controller.dto.OtpResponse;
import com.example.dev.controller.dto.ValidateOtpRequest;
import com.example.dev.entity.User;
import com.example.dev.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {
    private final OtpService otpService;

    @PostMapping("/generate")
    public OtpResponse generate(
            @AuthenticationPrincipal User user,
            @RequestBody GenerateOtpRequest request
    ) {
        String code = otpService.generateOtp(user, request.operationId());
        return new OtpResponse(code);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validate(
            @AuthenticationPrincipal User user,
            @RequestBody ValidateOtpRequest request
    ) {
        boolean result = otpService.validateOtp(
                user,
                request.code(),
                request.operationId()
        );

        if (result) {
            return ResponseEntity.ok("OTP valid");
        } else {
            return ResponseEntity.status(400).body("OTP invalid");
        }
    }
}

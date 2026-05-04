package com.example.dev.service;

import org.springframework.stereotype.Service;

@Service
public class EmailOtpSender implements OtpSender {
    public void send(String email, String code) {
        System.out.println("EMAIL to " + email + ": " + code);
    }
}

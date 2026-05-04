package com.example.dev.service;

import org.springframework.stereotype.Service;

@Service
public class SmsOtpSender implements OtpSender {
    public void send(String phone, String code) {
        System.out.println("SMS to " + phone + ": " + code);
    }
}
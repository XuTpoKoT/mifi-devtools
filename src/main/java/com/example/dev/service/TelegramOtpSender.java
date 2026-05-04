package com.example.dev.service;

import org.springframework.stereotype.Service;

@Service
public class TelegramOtpSender implements OtpSender {
    public void send(String chatId, String code) {
        System.out.println("Telegram to " + chatId + ": " + code);
    }
}
package com.example.dev.service;

public interface OtpSender {
    void send(String destination, String code);
}

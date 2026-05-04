package com.example.dev.controller.dto;

public record ValidateOtpRequest(
        Long operationId,
        String code
) {}

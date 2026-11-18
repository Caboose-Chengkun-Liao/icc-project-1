package com.chengkun.sms_backend.dto;

public record StudentRequest(
        String firstName,
        String lastName,
        String email
) {
}

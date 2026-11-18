package com.chengkun.sms_backend.dto;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}

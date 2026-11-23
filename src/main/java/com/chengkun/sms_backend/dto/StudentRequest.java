package com.chengkun.sms_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentRequest(
        @NotBlank(message = "firstName is required.")
        @Size(max = 50, message = "firstName is too long")
        String firstName,

        @NotBlank(message = "lastName is required")
        @Size(max = 50, message = "lastName must be at most 50 characters")
        String lastName,

        @NotBlank(message = "email is required")
        @Email(message = "email format is invalid")
        String email
) {
}

package com.microservies.intro.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record CustomerDto(
        @Pattern(regexp = "^[A-Za-z][a-z]+$", message = "Invalid name!")
        String name,
        @Pattern(regexp = "[0-9]{10}", message = "Invalid mobile number!")
        String mobileNumber,
        @Email(message = "Invalid email!")
        String email,
        AccountsDto accountsDto
) {}

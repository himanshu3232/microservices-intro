package com.microservies.intro.dto;

public record CustomerDto(
        String name,
        String mobileNumber,
        String email,
        AccountsDto accountsDto
) {}

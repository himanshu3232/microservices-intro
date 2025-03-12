package com.microservies.intro.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountsDto(
        Long accountNumber,
        Long customerId,
        @NotBlank(message = "account type cannot be blank!")
        String accountType,
        @NotBlank(message = "Branch Address cannot be blank!")
        String branchAddress
) {}

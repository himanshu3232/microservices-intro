package com.microservies.intro.dto;

public record AccountsDto(
        Long accountNumber,
        Long customerId,
        String accountType,
        String branchAddress
) {}

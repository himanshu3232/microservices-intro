package com.microservies.intro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class AccountsEntity extends BaseEntity{
    @Id
    private Long accountNumber;
    private Long customerId;
    private String accountType;
    private String branchAddress;
}

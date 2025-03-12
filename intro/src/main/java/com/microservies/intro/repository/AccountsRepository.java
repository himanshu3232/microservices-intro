package com.microservies.intro.repository;

import com.microservies.intro.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<AccountsEntity, Long> {
}

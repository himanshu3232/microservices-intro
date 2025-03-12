package com.microservies.intro.repository;

import com.microservies.intro.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByMobileNumber(String mobileNumber);
}

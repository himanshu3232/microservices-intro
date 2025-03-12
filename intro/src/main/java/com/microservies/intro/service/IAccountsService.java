package com.microservies.intro.service;

import com.microservies.intro.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto - CustomerDto object
     */
    void createAccount(CustomerDto customerDto);
}

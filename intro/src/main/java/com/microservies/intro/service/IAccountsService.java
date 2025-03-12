package com.microservies.intro.service;

import com.microservies.intro.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto - CustomerDto object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - mobile number of customer
     * @return CustomerDto object
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * @param customerDto - customerdto object
     * @return boolean
     */

    boolean updateAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - unique
     * @return boolean
     */

    boolean deleteAccount(String mobileNumber);
}

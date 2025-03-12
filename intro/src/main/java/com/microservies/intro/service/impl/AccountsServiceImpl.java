package com.microservies.intro.service.impl;

import com.microservies.intro.constants.AccountsConstants;
import com.microservies.intro.dto.CustomerDto;
import com.microservies.intro.dto.mapper.CustomerMapper;
import com.microservies.intro.entity.AccountsEntity;
import com.microservies.intro.entity.CustomerEntity;
import com.microservies.intro.exception.CustomerAlreadyExistsException;
import com.microservies.intro.repository.AccountsRepository;
import com.microservies.intro.repository.CustomerRepository;
import com.microservies.intro.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final Random random = new Random();

    @Override
    public void createAccount(CustomerDto customerDto) {
        if(customerRepository.findByMobileNumber(customerDto.mobileNumber()).isPresent()){
            throw new CustomerAlreadyExistsException("Customer with this mobile number already exists! " + customerDto.mobileNumber());
        }
        var customer = CustomerMapper.mapToCustomerEntity(customerDto);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy(customer.getName());
        CustomerEntity savedCustomer = customerRepository.save(customer);
        AccountsEntity accounts = createNewAccount(savedCustomer);
        accountsRepository.save(accounts);
    }

    private AccountsEntity createNewAccount(CustomerEntity customerEntity){
        var account = new AccountsEntity(
                1000000000000000L * random.nextInt(900000000)
                , customerEntity.getCustomerId()
                , AccountsConstants.SAVINGS
                , AccountsConstants.ADDRESS
        );
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy(customerEntity.getCreatedBy());
        return account;
    }
}

package com.microservies.intro.service.impl;

import com.microservies.intro.constants.AccountsConstants;
import com.microservies.intro.dto.AccountsDto;
import com.microservies.intro.dto.CustomerDto;
import com.microservies.intro.dto.mapper.AccountsMapper;
import com.microservies.intro.dto.mapper.CustomerMapper;
import com.microservies.intro.entity.AccountsEntity;
import com.microservies.intro.entity.CustomerEntity;
import com.microservies.intro.exception.CustomerAlreadyExistsException;
import com.microservies.intro.exception.ResourceNotFoundException;
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
        CustomerEntity savedCustomer = customerRepository.save(customer);
        AccountsEntity accounts = createNewAccount(savedCustomer);
        accountsRepository.save(accounts);
    }

    @Override
    public CustomerDto fetchAccount(final String mobileNumber) {
        CustomerEntity customerEntity = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("CustomerEntity", "mobileNumber", mobileNumber)
        );
        AccountsEntity accountsEntity = accountsRepository.findByCustomerId(customerEntity.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("CustomerEntity", "mobileNumber", mobileNumber));

        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accountsEntity);

        return CustomerMapper.mapToCustomerDto(customerEntity, accountsDto);
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        AccountsDto accountsDto = customerDto.accountsDto();
        if(accountsDto != null){
            AccountsEntity accountsEntity = accountsRepository
                    .findById(accountsDto.accountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Accounts", "Account Number", accountsDto.accountNumber().toString()));

            updateAccount(accountsDto,accountsEntity);

            accountsRepository.save(accountsEntity);

            CustomerEntity customerEntity = customerRepository
                    .findById(accountsDto.customerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", accountsDto.customerId().toString()));


            updateCustomer(customerDto, customerEntity);

            customerRepository.save(customerEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        CustomerEntity customerEntity = customerRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));

        customerRepository.deleteById(customerEntity.getCustomerId());
        accountsRepository.deleteByCustomerId(customerEntity.getCustomerId());

        return true;
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

    private void updateAccount(AccountsDto accountsDto, AccountsEntity accountsEntity){
        accountsEntity.setAccountType(accountsDto.accountType());
        accountsEntity.setBranchAddress(accountsDto.branchAddress());
    }

    private void updateCustomer(CustomerDto customerDto, CustomerEntity customerEntity){
        customerEntity.setName(customerDto.name());
        customerEntity.setEmail(customerDto.email());
        customerEntity.setMobileNumber(customerDto.mobileNumber());
    }
}

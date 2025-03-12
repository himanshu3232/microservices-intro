package com.microservies.intro.dto.mapper;

import com.microservies.intro.dto.CustomerDto;
import com.microservies.intro.entity.CustomerEntity;

public final class CustomerMapper {
    private CustomerMapper(){}

    public static CustomerEntity mapToCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDto.name());
        customerEntity.setMobileNumber(customerDto.mobileNumber());
        customerEntity.setEmail(customerDto.email());
        return customerEntity;
    }

    public static CustomerDto mapToCustomerDto(CustomerEntity customerEntity){
        return new CustomerDto(
                customerEntity.getName()
                , customerEntity.getMobileNumber()
                , customerEntity.getEmail()
        );
    }
}

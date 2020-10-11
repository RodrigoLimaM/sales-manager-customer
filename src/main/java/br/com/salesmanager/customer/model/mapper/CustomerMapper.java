package br.com.salesmanager.customer.model.mapper;

import br.com.salesmanager.customer.model.Customer;
import br.com.salesmanager.customer.model.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapCustomerDTOToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .balance(customerDTO.getBalance())
                .build();
    }
}

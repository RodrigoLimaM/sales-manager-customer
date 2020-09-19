package br.com.salesmanager.customer.service;

import br.com.salesmanager.customer.model.Customer;
import br.com.salesmanager.customer.model.dto.CustomerDTO;
import br.com.salesmanager.customer.model.mapper.CustomerMapper;
import br.com.salesmanager.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    public Customer insert(CustomerDTO customerDTO) {
        return customerRepository.insert(customerMapper.mapCustomerDTOToCustomer(customerDTO));
    }
}

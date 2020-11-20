package br.com.salesmanager.customer.service;

import br.com.salesmanager.customer.config.exception.CustomerAlreadyExistsException;
import br.com.salesmanager.customer.model.Customer;
import br.com.salesmanager.customer.model.dto.CustomerDTO;
import br.com.salesmanager.customer.model.mapper.CustomerMapper;
import br.com.salesmanager.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    public Customer insert(CustomerDTO customerDTO) {
        if(!this.findByEmail(customerDTO.getEmail()).isPresent())
            return customerRepository.insert(customerMapper.mapCustomerDTOToCustomer(customerDTO));
        else
            throw new CustomerAlreadyExistsException();
    }

    public Optional<Customer> findById(String customerId){
        return customerRepository.findById(customerId);
    }

    public Customer updateBalance(Customer customer, BigDecimal orderValue) {
        customer.setBalance(customer.getBalance().subtract(orderValue));
        return this.save(customer);
    }

    public boolean hasAvailableBalance(BigDecimal orderValue, Customer customer) {
        return customer.getBalance().compareTo(orderValue) >= 0;
    }

    public Optional<Customer> findByEmail(String email) {
        return Optional.ofNullable(customerRepository.findByEmail(email));
    }

    private Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

}

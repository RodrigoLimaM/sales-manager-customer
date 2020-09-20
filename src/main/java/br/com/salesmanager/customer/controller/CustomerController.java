package br.com.salesmanager.customer.controller;

import br.com.salesmanager.customer.model.Customer;
import br.com.salesmanager.customer.model.dto.CustomerDTO;
import br.com.salesmanager.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@Validated
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI("/customer"))
                .body(customerService.insert(customerDTO));
    }
}

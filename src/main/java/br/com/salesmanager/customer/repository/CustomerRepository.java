package br.com.salesmanager.customer.repository;

import br.com.salesmanager.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Override
    <S extends Customer> S insert(S s);
}

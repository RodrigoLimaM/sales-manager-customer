package br.com.salesmanager.customer.config.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException() {
        super("Customer already exists");
    }
}

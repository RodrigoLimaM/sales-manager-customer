package br.com.salesmanager.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class SalesManagerCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagerCustomerApplication.class, args);
	}

}

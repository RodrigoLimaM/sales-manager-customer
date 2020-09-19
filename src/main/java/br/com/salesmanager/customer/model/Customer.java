package br.com.salesmanager.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "customers")
@Getter
@ToString
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @Field(name = "_id")
    private final String customerId;

    @Field(name = "customer_balance")
    private BigDecimal balance;

}

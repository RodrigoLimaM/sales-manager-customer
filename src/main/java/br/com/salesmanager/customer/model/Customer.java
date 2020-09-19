package br.com.salesmanager.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@Document(collection = "customers")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Customer {

    @MongoId(value = FieldType.OBJECT_ID)
    private final String _id;

    @Field(name = "customer_balance")
    private BigDecimal balance;

}

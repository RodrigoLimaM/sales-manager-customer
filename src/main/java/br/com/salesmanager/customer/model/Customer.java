package br.com.salesmanager.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Field(name = "customer_name")
    private String name;

    @Field(name = "customer_email")
    private String email;

    @Field(name = "customer_password")
    private String password;

    @CreatedDate
    @Field(name = "creation_date")
    private final LocalDateTime creationDate;

    @LastModifiedDate
    @Field(name = "update_date")
    private LocalDateTime updateDate;

}

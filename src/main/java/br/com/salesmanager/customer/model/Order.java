package br.com.salesmanager.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    private String customerId;

    private String orderId;

    private BigDecimal value;

    private String orderStatus;
}

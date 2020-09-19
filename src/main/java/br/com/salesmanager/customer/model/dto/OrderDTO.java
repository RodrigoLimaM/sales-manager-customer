package br.com.salesmanager.customer.model.dto;

import br.com.salesmanager.customer.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderDTO {

    @NotBlank
    @NotNull
    private String customerId;

    @NotBlank
    @NotNull
    private String orderId;

    private OrderStatus orderStatus;
}

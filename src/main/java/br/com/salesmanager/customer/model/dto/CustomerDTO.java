package br.com.salesmanager.customer.model.dto;

import br.com.salesmanager.customer.validation.Email;
import br.com.salesmanager.customer.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CustomerDTO {

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private BigDecimal balance;

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Password
    private String password;

}

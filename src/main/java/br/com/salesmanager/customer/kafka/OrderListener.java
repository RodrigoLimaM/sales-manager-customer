package br.com.salesmanager.customer.kafka;

import br.com.salesmanager.customer.model.Customer;
import br.com.salesmanager.customer.model.dto.OrderDTO;
import br.com.salesmanager.customer.model.enums.OrderStatus;
import br.com.salesmanager.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class OrderListener {

    private static final String TOPIC = "yje6oae7-NEW_ORDER";

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderStatusChangeProducer orderStatusChangeProducer;

    @KafkaListener(topics = "yje6oae7-NEW_ORDER")
    public void consume(Order order) {

        log.info("Message Listened: {}, Topic: {}", order, TOPIC);

        Optional<Customer> optionalCustomer = customerService.findById(order.getCustomerId());

        boolean isValidTransaction = optionalCustomer
                .map(cust -> customerService.hasAvailableBalance(order.getOrderTotalValue(), cust))
                .orElseThrow(() -> new RuntimeException("Null customer"));

        OrderDTO orderDTO = OrderDTO
                .builder()
                .customerId(order.getCustomerId())
                .orderId(order.getOrderId())
                .build();

        Customer customer = optionalCustomer.get();

        if (isValidTransaction) {
            orderDTO.setOrderStatus(OrderStatus.APPROVED);
            Customer updatedCustomer = customerService.updateBalance(customer, order.getOrderTotalValue());
            log.info("Customer balance update: {}", updatedCustomer);
        } else {
            orderDTO.setOrderStatus(OrderStatus.CANCELLED);
        }

        log.info("Order update: {}", orderDTO);
        orderStatusChangeProducer.sendMessage(orderDTO);
    }

}

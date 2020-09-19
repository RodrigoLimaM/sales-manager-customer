package br.com.salesmanager.customer.service;

import br.com.salesmanager.customer.model.Order;
import br.com.salesmanager.customer.model.dto.OrderDTO;
import br.com.salesmanager.customer.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderListener {

    private static final String TOPIC = "NEW_ORDER";

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderStatusChangeProducer orderStatusChangeProducer;

    @KafkaListener(topics = "NEW_ORDER", groupId = "sales_manager", containerFactory = "userKafkaListenerContainerFactory")
    public void consume(Order order) {

        log.info("Message Listened: {}, Topic: {}", order, TOPIC);

        var optionalCustomer = customerService.findById(order.getCustomerId());

        boolean isValidTransaction = optionalCustomer
                .map(customer1 -> customerService.hasAvailableBalance(order.getValue(), customer1))
                .orElseThrow(() -> new RuntimeException("Null customer"));

        var orderDTO = OrderDTO
                .builder()
                .customerId(order.getCustomerId())
                .orderId(order.getOrderId())
                .build();

        var customer = optionalCustomer.get();

        if (isValidTransaction) {
            orderDTO.setOrderStatus(OrderStatus.FINISHED);
            var updatedCustomer = customerService.updateBalance(customer, order.getValue());
            log.info("Customer balance update: {}", updatedCustomer);
        } else {
            orderDTO.setOrderStatus(OrderStatus.CANCELLED);
        }

        log.info("Order update: {}", orderDTO);
        orderStatusChangeProducer.sendMessage(orderDTO);
    }

}

package br.com.salesmanager.customer.service;

import br.com.salesmanager.customer.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderListener {

    private static final String TOPIC = "NEW_ORDER";

    @KafkaListener(topics = "NEW_ORDER", groupId = "sales_manager", containerFactory = "userKafkaListenerContainerFactory")
    public void consume(Order order) {
        log.info("*************Value: {}, Topic: {}", order, TOPIC);
    }

}

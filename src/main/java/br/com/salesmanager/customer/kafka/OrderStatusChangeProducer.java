package br.com.salesmanager.customer.kafka;

import br.com.salesmanager.customer.model.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderStatusChangeProducer {

    private static final String TOPIC = "ORDER_STATUS_CHANGE";

    @Autowired
    private KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public void sendMessage(OrderDTO message) {
        log.info("Produced message: {}, Topic: {}", message, TOPIC);
        kafkaTemplate.send(TOPIC, message);
    }
}

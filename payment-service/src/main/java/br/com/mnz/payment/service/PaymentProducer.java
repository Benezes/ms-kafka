package br.com.mnz.payment.service;

import br.com.mnz.payment.dto.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
@Log4j2
public class PaymentProducer {

    public static final String PAYMENT_TOPIC = "payment-topic";

    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    public void sendTopicInKafka(PaymentResponse payment) {
        log.info("Sending message: {} with the to te topic: {}", payment, PAYMENT_TOPIC);
        kafkaTemplate.send(PAYMENT_TOPIC, payment);
        log.info("Send payment with success {} ", payment);
    }
}

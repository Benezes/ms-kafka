package com.gabriel.payment.service;

import com.gabriel.payment.model.Payment;
import com.gabriel.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentService {

    public static final String PAYMENT_TOPIC = "payment-topic";

    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    public Payment createPayment(Payment payment) {
        payment = paymentRepository.save(payment);
        log.info("PAYMENT SAVED ::: {}", payment);

        sendTopicInKafka(payment);

        return payment;
    }

    private void sendTopicInKafka(Payment payment) {
        log.info("Sending message: {} with the to te topic: {}", payment, PAYMENT_TOPIC);
        kafkaTemplate.send(PAYMENT_TOPIC, payment)
                .addCallback(
                        success -> {
                            if (success != null) {
                                log.info("Send payment with success {} ", payment);
                                log.info("Partition {} ", success.getRecordMetadata().partition());
                                log.info("Offset {} ", success.getRecordMetadata().offset());
                            }
                        },
                        error -> log.error("Error sending message {0}", error)
                );
    }
}

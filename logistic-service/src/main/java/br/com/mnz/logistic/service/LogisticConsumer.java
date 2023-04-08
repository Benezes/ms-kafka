package br.com.mnz.logistic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class LogisticConsumer {

    private final LogisticService logisticService;

    @KafkaListener(topics = "payment-topic", groupId = "payment-approved-group", containerFactory = "jsonContainerFactory")
    public void listenerApprovedPayments(ConsumerRecord<String, Object> consumerRecord) {
        log.info("PAYMENT APPROVED GROUP!");

        String consumerRecordString = refactorConsumerRecordValue(consumerRecord.value());
        log.info("Received payment {} ", consumerRecordString);
        logisticService.createLogisticOrder(consumerRecordString);

        log.info("Partition {} ", consumerRecord.partition());
        log.info("Offset {} ", consumerRecord.offset());
    }

    private String refactorConsumerRecordValue(Object value) {
        return value.toString();
    }

}

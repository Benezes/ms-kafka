package br.com.mnz.logistic.service;

import br.com.mnz.logistic.dto.response.PaymentResponse;
import br.com.mnz.logistic.models.LogisticModel;
import br.com.mnz.logistic.repository.LogisticRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogisticService {

    private final ObjectMapper objectMapper;
    private final LogisticRepository logisticRepository;

    public void createLogisticOrder(String value) {
        try {
            PaymentResponse payment = objectMapper.readValue(value, PaymentResponse.class);

            LogisticModel logisticModel = LogisticModel
                    .builder()
                    .paymentId(payment.getPaymentId())
                    .productId(payment.getProductId())
                    .build();

            logisticRepository.save(logisticModel);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package br.com.mnz.logistic.service;

import br.com.mnz.logistic.dto.response.LogisticResponse;
import br.com.mnz.logistic.dto.response.PaymentResponse;
import br.com.mnz.logistic.dto.response.ProductResponse;
import br.com.mnz.logistic.external.WebFluxCall;
import br.com.mnz.logistic.models.LogisticModel;
import br.com.mnz.logistic.repository.LogisticRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogisticService {

    private final ObjectMapper objectMapper;
    private final LogisticRepository logisticRepository;
    private final WebFluxCall webFluxCall;

    public void createLogisticOrder(String value) {
        try {
            PaymentResponse payment = objectMapper.readValue(value, PaymentResponse.class);

            LogisticModel logisticModel = LogisticModel
                    .builder()
                    .logisticId(UUID.randomUUID())
                    .paymentId(payment.getPaymentId())
                    .productId(payment.getProductId())
                    .build();

            logisticRepository.save(logisticModel);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LogisticResponse> findAllLogisticProducts() {

        List<LogisticResponse> logisticList = new ArrayList<>();

        logisticRepository.findAll().forEach(logistic -> {
            UUID productId = logistic.getProductId();
            ProductResponse productResponse = webFluxCall.getProductById(productId);
            logisticList.add(new LogisticResponse(productResponse));
        });

        return logisticList;
    }
}

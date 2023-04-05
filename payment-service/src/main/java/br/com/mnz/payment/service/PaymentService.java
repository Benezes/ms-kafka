package br.com.mnz.payment.service;

import br.com.mnz.payment.dto.request.PaymentRequest;
import br.com.mnz.payment.dto.response.PaymentResponse;
import br.com.mnz.payment.dto.response.ProductResponse;
import br.com.mnz.payment.external.WebFluxCall;
import br.com.mnz.payment.models.CardModel;
import br.com.mnz.payment.models.PaymentModel;
import br.com.mnz.payment.repository.CardRepository;
import br.com.mnz.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CardRepository cardRepository;
    private final WebFluxCall externalCall;
    private final PaymentProducer paymentProducer;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        ProductResponse product = externalCall.getProductByName(paymentRequest.getProductName());

        CardModel card = cardRepository.findByCardNumber(paymentRequest.getCard().getCardNumber());

        if (card == null) {
            card = CardModel
                    .builder()
                    .cardName(paymentRequest.getCard().getCardName())
                    .cardNumber(paymentRequest.getCard().getCardNumber())
                    .cardExpire(paymentRequest.getCard().getCardExpire())
                    .cardSecurityNumber(paymentRequest.getCard().getCardSecurityNumber())
                    .build();
            cardRepository.save(card);
        }

        PaymentModel payment = PaymentModel
                .builder()
                .productId(product.getProductId())
                .quantity(paymentRequest.getQuantity())
                .card(card)
                .price(product.getPrice() * paymentRequest.getQuantity())
                .build();

        PaymentResponse paymentResponse = new PaymentResponse(paymentRepository.save(payment));
        log.info("Create payment.: {} of the project.: {}", paymentResponse, product);
        paymentProducer.sendTopicInKafka(paymentResponse);

        return paymentResponse;
    }


}

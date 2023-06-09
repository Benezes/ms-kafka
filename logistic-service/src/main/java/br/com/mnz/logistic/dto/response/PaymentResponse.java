package br.com.mnz.logistic.dto.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
public class PaymentResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID paymentId;
    private UUID productId;
    private Integer quantity;
    private CardResponse card;
    private Double price;
}

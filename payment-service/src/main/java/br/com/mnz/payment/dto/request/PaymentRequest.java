package br.com.mnz.payment.dto.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PaymentRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String productName;
    private Integer quantity;
    private CardRequest card;
}

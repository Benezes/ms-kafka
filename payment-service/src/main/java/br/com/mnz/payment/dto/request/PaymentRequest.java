package br.com.mnz.payment.dto.request;

import br.com.mnz.payment.models.CardModel;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
public class PaymentRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID productId;
    private Integer quantity;
    private CardRequest card;
}

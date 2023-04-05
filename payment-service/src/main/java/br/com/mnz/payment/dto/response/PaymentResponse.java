package br.com.mnz.payment.dto.response;

import br.com.mnz.payment.models.CardModel;
import br.com.mnz.payment.models.PaymentModel;
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

    public PaymentResponse(PaymentModel entity) {
        this.paymentId = entity.getPaymentId();
        this.productId = entity.getProductId();
        this.quantity = entity.getQuantity();
        this.card = new CardResponse(entity.getCard());
        this.price = entity.getPrice();
    }
}

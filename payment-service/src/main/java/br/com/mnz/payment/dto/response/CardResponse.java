package br.com.mnz.payment.dto.response;

import br.com.mnz.payment.models.CardModel;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
public class CardResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID cardId;
    private String cardName;
    private String cardNumber;
    private String cardExpire;
    private String cardSecurityNumber;

    public CardResponse(CardModel entity) {
        this.cardId = entity.getCardId();
        this.cardName = entity.getCardName();
        this.cardNumber = entity.getCardNumber();
        this.cardExpire = entity.getCardExpire();
        this.cardSecurityNumber = entity.getCardSecurityNumber();
    }
}

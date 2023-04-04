package br.com.mnz.payment.dto.response;

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

}

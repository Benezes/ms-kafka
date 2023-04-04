package br.com.mnz.payment.dto.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CardRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String cardName;
    private String cardNumber;
    private String cardExpire;
    private String cardSecurityNumber;
}

package br.com.mnz.payment.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "card_tb")
@Data
public class CardModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cardId;

    @Column(nullable = false)
    private String cardName;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String cardExpire;

    @Column(nullable = false)
    private String cardSecurityNumber;

    @OneToMany(mappedBy = "card")
    private List<PaymentModel> payments;
}

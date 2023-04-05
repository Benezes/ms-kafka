package br.com.mnz.payment.repository;

import br.com.mnz.payment.models.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardModel, UUID> {

    CardModel findByCardNumber(String cardNumber);
}

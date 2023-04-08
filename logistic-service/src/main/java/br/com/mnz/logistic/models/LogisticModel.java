package br.com.mnz.logistic.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Document(collection = "logistic")
@Data
@Builder
public class LogisticModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID logisticId;
    private UUID productId;
    private UUID paymentId;

}

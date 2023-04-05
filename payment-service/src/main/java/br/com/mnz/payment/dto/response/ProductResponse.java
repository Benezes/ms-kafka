package br.com.mnz.payment.dto.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
public class ProductResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID productId;
    private String name;
    private Double price;
    private String description;

}

package br.com.mnz.product.dto.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ProductRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private Double price;
    private String description;
}

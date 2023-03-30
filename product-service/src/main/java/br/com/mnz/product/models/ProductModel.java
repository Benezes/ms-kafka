package br.com.mnz.product.models;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Document(collection = "product")
@Data
@Builder
public class ProductModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID productId;

    @Field("product_name")
    private String name;

    @Field("product_price")
    private Double price;

    @Field("product_description")
    private String description;
}

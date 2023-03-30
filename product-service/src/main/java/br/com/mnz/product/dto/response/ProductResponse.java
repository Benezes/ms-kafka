package br.com.mnz.product.dto.response;

import br.com.mnz.product.models.ProductModel;
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

    public ProductResponse(ProductModel model) {
        this.productId = model.getProductId();
        this.name = model.getName();
        this.price = model.getPrice();
        this.description = model.getDescription();
    }
}

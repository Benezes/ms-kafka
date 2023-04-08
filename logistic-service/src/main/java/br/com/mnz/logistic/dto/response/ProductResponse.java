package br.com.mnz.logistic.dto.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
public class ProductResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;

}

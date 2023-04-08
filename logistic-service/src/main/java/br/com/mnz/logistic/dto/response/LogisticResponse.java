package br.com.mnz.logistic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogisticResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ProductResponse productResponse;

}

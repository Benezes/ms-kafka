package br.com.mnz.logistic.external;

import br.com.mnz.logistic.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Log4j2
public class WebFluxCall {

    private final WebClient webClient;
    private static final String PORT = "8080";
    private static final String PRODUCTS_API = "http://localhost:" + PORT + "/api/products/id/";

    public ProductResponse getProductById(final UUID productId) {
        log.info("Finding product: {}", productId);
        return webClient.get().uri(PRODUCTS_API + productId)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }
}

package br.com.mnz.payment.external;

import br.com.mnz.payment.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Log4j2
public class WebFluxCall {

    private final WebClient webClient;
    private static final String PORT = "8080";
    private static final String PRODUCTS_API = "http://localhost:" + PORT + "/api/products/";

    public ProductResponse getProductByName(final String productName) {
        log.info("Finding product: {}", productName);
        return webClient.get().uri(PRODUCTS_API + productName)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }
}

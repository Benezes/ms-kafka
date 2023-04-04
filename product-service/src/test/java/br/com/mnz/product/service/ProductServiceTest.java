package br.com.mnz.product.service;

import br.com.mnz.product.dto.request.ProductRequest;
import br.com.mnz.product.dto.response.ProductResponse;
import br.com.mnz.product.models.ProductModel;
import br.com.mnz.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("Test find all products")
    void shouldFindAllProducts() {
        ProductModel product1 = buildProduct();

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1));

        List<ProductResponse> result = productService.findAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product1.getProductId(), result.get(0).getProductId());
    }

    @Test
    @DisplayName("Test create product")
    void shouldCreateProductWithSuccess() {
        ProductModel product = buildProduct();

        when(productRepository.save(any(ProductModel.class))).thenReturn(product);

        ProductRequest productRequest = buildProductRequest(product);

        ProductResponse result = productService.createProduct(productRequest);

        assertNotNull(result);
        assertEquals(product.getProductId(), result.getProductId());
    }

    private ProductRequest buildProductRequest(ProductModel product) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setDescription(product.getDescription());
        productRequest.setName(product.getName());
        productRequest.setDescription(product.getDescription());
        return productRequest;
    }

    private ProductModel buildProduct() {
        UUID productId = UUID.randomUUID();
        String productName = "Product name";
        BigDecimal productPrice = BigDecimal.valueOf(10.99);
        String productDescription = "Product description";
        return ProductModel
                .builder()
                .productId(productId)
                .name(productName)
                .price(productPrice.doubleValue())
                .description(productDescription)
                .build();
    }
}
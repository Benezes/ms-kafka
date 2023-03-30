package br.com.mnz.product.service;

import br.com.mnz.product.dto.request.ProductRequest;
import br.com.mnz.product.dto.response.ProductResponse;
import br.com.mnz.product.models.ProductModel;
import br.com.mnz.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .toList();
    }

    public ProductResponse createProduct(final ProductRequest productRequest) {
        ProductModel productModel = ProductModel.builder()
                .productId(UUID.randomUUID())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();
        return new ProductResponse(productRepository.save(productModel));
    }

    public ProductResponse findProductByName(final String productName) {
        return new ProductResponse(productRepository.findByName(productName));
    }

    public void deleteProduct(UUID productId) {
        productRepository.findById(productId).ifPresent(productRepository::delete);
    }

    public ProductResponse udpateProduct(UUID productId, ProductRequest productRequest) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if(product.isEmpty())
            throw new RuntimeException();

        BeanUtils.copyProperties(productRequest, product.get(), "productId");
        return new ProductResponse(productRepository.save(product.get()));
    }
}

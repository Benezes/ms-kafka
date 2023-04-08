package br.com.mnz.product.service;

import br.com.mnz.product.dto.request.ProductRequest;
import br.com.mnz.product.dto.response.ProductResponse;
import br.com.mnz.product.models.ProductModel;
import br.com.mnz.product.repository.ProductRepository;
import br.com.mnz.product.service.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream().map(ProductResponse::new).toList();
    }

    public ProductResponse createProduct(final ProductRequest productRequest) {
        ProductModel productModel =
                ProductModel
                        .builder()
                        .productId(UUID.randomUUID())
                        .name(productRequest.getName())
                        .price(productRequest.getPrice())
                        .description(productRequest.getDescription())
                        .build();
        return new ProductResponse(productRepository.save(productModel));
    }

    public ProductResponse findProductByName(final String productName) {
        ProductModel product = productRepository.findByName(productName);
        if(product == null) {
            throw new ProductNotFoundException("product name {" + productName + "} not found");
        }
        return new ProductResponse(product);
    }

    public void deleteProduct(UUID productId) {
        productRepository.findById(productId).ifPresentOrElse(productRepository::delete, () -> {
            throw new ProductNotFoundException("product id {" + productId + "} not found");
        });
    }

    public ProductResponse udpateProduct(UUID productId, ProductRequest productRequest) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new ProductNotFoundException("product id {" + productId + "} not found");
        }

        BeanUtils.copyProperties(productRequest, product.get(), "productId");
        return new ProductResponse(productRepository.save(product.get()));
    }

    public ProductResponse findProductById(UUID productId) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new ProductNotFoundException("product id {" + productId + "} not found");
        }

        return new ProductResponse(product.get());
    }
}

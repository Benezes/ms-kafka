package br.com.mnz.product.controller;

import br.com.mnz.product.dto.request.ProductRequest;
import br.com.mnz.product.dto.response.ProductResponse;
import br.com.mnz.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> postProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
    }

    @GetMapping("/{productName}")
    public ResponseEntity<ProductResponse> getProductByName(@PathVariable(value = "productName") final String productName) {
        return ResponseEntity.ok(productService.findProductByName(productName));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable(value = "productId") final UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> deleteProductById(@PathVariable(value = "productId") final UUID productId, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.udpateProduct(productId, productRequest));
    }
}

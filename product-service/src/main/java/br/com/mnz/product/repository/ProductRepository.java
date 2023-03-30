package br.com.mnz.product.repository;

import br.com.mnz.product.models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<ProductModel, UUID> {

    ProductModel findByName(String productName);

}

package br.com.mnz.logistic.repository;

import br.com.mnz.logistic.models.LogisticModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface LogisticRepository extends MongoRepository<LogisticModel, UUID> {
}

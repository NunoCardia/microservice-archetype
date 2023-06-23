package it.pkg.nosql.repository;

import it.pkg.nosql.document.TestDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends MongoRepository<TestDocument, Long> {
}
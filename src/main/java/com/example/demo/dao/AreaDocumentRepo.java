package com.example.demo.dao;

import com.example.demo.models.AreaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaDocumentRepo extends MongoRepository<AreaDocument, Integer> {
}

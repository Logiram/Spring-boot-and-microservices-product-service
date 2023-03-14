package com.codefreak.productservice.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.codefreak.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}

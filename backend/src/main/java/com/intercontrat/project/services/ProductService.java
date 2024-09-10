package com.intercontrat.project.services;

import com.intercontrat.project.entities.Product;
import com.intercontrat.project.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private KafkaTemplate<String, Product> kafkaTemplate;

    private static final String TOPIC = "product_created";

    public ProductService(ProductRepository productRepository, KafkaTemplate<String, Product> kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        kafkaTemplate.send(TOPIC, savedProduct);

        return savedProduct;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

package com.intercontrat.project.services;

import com.intercontrat.project.entities.Product;
import com.intercontrat.project.repositories.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private KafkaTemplate<String, Product> kafkaTemplate;

    private static final String TOPIC = "product_created";
    private final List<Product> productList = new ArrayList<>();
    private long nextId = 1;

    public ProductService(ProductRepository productRepository, KafkaTemplate<String, Product> kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Product createProduct(Product product) {
        product.setId(nextId++);
        productList.add(product);

        kafkaTemplate.send(TOPIC, product);

        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }
}

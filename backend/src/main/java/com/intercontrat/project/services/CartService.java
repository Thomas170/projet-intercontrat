package com.intercontrat.project.services;

import com.intercontrat.project.entities.Product;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @KafkaListener(topics = "product_created", groupId = "cart-group")
    public void logProductCreated(Product product) {
        System.out.println("Produit créé: " + product.getName() + " avec un prix de " + product.getPrice());
    }
}

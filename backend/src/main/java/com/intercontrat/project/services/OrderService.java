package com.intercontrat.project.services;

import com.intercontrat.project.entities.Order;
import com.intercontrat.project.entities.Product;
import com.intercontrat.project.enums.OrderStatus;
import com.intercontrat.project.exceptions.ResourceNotFoundException;
import com.intercontrat.project.repositories.OrderRepository;
import com.intercontrat.project.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order addProductToOrder(Long orderId, Long productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (orderOptional.isPresent() && productOptional.isPresent()) {
            Order order = orderOptional.get();
            Product product = productOptional.get();
            order.getProducts().add(product);
            return orderRepository.save(order);
        }

        throw new ResourceNotFoundException("Order or Product not found");
    }

    public Order removeProductFromOrder(Long orderId, Long productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (orderOptional.isPresent() && productOptional.isPresent()) {
            Order order = orderOptional.get();
            Product product = productOptional.get();
            order.getProducts().remove(product);
            return orderRepository.save(order);
        }

        throw new ResourceNotFoundException("Order or Product not found");
    }

    public Order getOrCreatePendingOrder() {
        return orderRepository.findByStatus(OrderStatus.PENDING)
                .orElseGet(() -> {
                    Order newOrder = new Order();
                    newOrder.setStatus(OrderStatus.PENDING);

                    return orderRepository.save(newOrder);
                });
    }

    public Order shipOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            if (order.getStatus() == OrderStatus.PENDING) {
                order.setStatus(OrderStatus.SHIPPED);
                order.setOrderDate(new Date());
                order.setDeliveryDate(new Date());

                return orderRepository.save(order);
            }

            throw new RuntimeException("Order is not in pending status");
        }

        throw new ResourceNotFoundException("Order not found");
    }

    public Order cancelOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.getProducts().clear();
            return orderRepository.save(order);
        }

        throw new ResourceNotFoundException("Order not found");
    }

    @KafkaListener(topics = "product_created", groupId = "cart-group")
    public void logProductCreated(Product product) {
        System.out.println("Produit créé: " + product.getName() + " avec un prix de " + product.getPrice());
    }
}

package com.intercontrat.project.services;

import com.intercontrat.project.entities.ClientOrder;
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

    public ClientOrder addProductToOrder(Long orderId, Long productId) {
        Optional<ClientOrder> orderOptional = orderRepository.findById(orderId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (orderOptional.isPresent() && productOptional.isPresent()) {
            ClientOrder order = orderOptional.get();
            Product product = productOptional.get();
            order.getProducts().add(product);
            return orderRepository.save(order);
        }

        throw new ResourceNotFoundException("Order or Product not found");
    }

    public ClientOrder removeProductFromOrder(Long orderId, Long productId) {
        Optional<ClientOrder> orderOptional = orderRepository.findById(orderId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (orderOptional.isPresent() && productOptional.isPresent()) {
            ClientOrder order = orderOptional.get();
            Product product = productOptional.get();
            order.getProducts().remove(product);
            return orderRepository.save(order);
        }

        throw new ResourceNotFoundException("Order or Product not found");
    }

    public ClientOrder getOrCreatePendingOrder() {
        Optional<ClientOrder> order = orderRepository.findByStatus(OrderStatus.PENDING);

        if (order.isPresent()) {
            return order.get();
        } else {
            ClientOrder newOrder = new ClientOrder();
            newOrder.setStatus(OrderStatus.PENDING);

            return orderRepository.save(newOrder);
        }
    }

    public ClientOrder shipOrder(Long orderId) {
        Optional<ClientOrder> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            ClientOrder order = orderOptional.get();
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

    public ClientOrder cancelOrder(Long orderId) {
        Optional<ClientOrder> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            ClientOrder order = orderOptional.get();
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

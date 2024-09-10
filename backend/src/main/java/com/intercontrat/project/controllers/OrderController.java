package com.intercontrat.project.controllers;

import com.intercontrat.project.dtos.OrderDTO;
import com.intercontrat.project.entities.Order;
import com.intercontrat.project.mappers.OrderMapper;
import com.intercontrat.project.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Order> addProductToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        Order updatedOrder = orderService.addProductToOrder(orderId, productId);

        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Order> removeProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        Order updatedOrder = orderService.removeProductFromOrder(orderId, productId);

        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/pending")
    public ResponseEntity<Order> getPendingOrder() {
        Order order = orderService.getOrCreatePendingOrder();

        return ResponseEntity.ok(order);
    }

    @PostMapping("/{orderId}/ship")
    public ResponseEntity<Order> shipOrder(@PathVariable Long orderId) {
        Order updatedOrder = orderService.shipOrder(orderId);

        return ResponseEntity.ok(updatedOrder);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        Order updatedOrder = orderService.cancelOrder(orderId);

        return ResponseEntity.ok(updatedOrder);
    }
}

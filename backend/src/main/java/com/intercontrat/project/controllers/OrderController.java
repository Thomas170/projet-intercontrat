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
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/{orderId}/products/{productId}")
    public ResponseEntity<OrderDTO> addProductToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        Order updatedOrder = orderService.addProductToOrder(orderId, productId);

        return ResponseEntity.ok(orderMapper.toOrderDTO(updatedOrder));
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public ResponseEntity<OrderDTO> removeProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        Order updatedOrder = orderService.removeProductFromOrder(orderId, productId);

        return ResponseEntity.ok(orderMapper.toOrderDTO(updatedOrder));
    }

    @GetMapping("/pending")
    public ResponseEntity<OrderDTO> getPendingOrder() {
        Order order = orderService.getOrCreatePendingOrder();

        return ResponseEntity.ok(orderMapper.toOrderDTO(order));
    }

    @PostMapping("/{orderId}/ship")
    public ResponseEntity<OrderDTO> shipOrder(@PathVariable Long orderId) {
        Order updatedOrder = orderService.shipOrder(orderId);

        return ResponseEntity.ok(orderMapper.toOrderDTO(updatedOrder));
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId) {
        Order updatedOrder = orderService.cancelOrder(orderId);

        return ResponseEntity.ok(orderMapper.toOrderDTO(updatedOrder));
    }
}

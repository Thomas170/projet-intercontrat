package com.intercontrat.project.controllers;

import com.intercontrat.project.entities.ClientOrder;
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
    public ResponseEntity<ClientOrder> addProductToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        ClientOrder updatedOrder = orderService.addProductToOrder(orderId, productId);

        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public ResponseEntity<ClientOrder> removeProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        ClientOrder updatedOrder = orderService.removeProductFromOrder(orderId, productId);

        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/pending")
    public ResponseEntity<ClientOrder> getPendingOrder() {
        ClientOrder ClientOrder = orderService.getOrCreatePendingOrder();

        return ResponseEntity.ok(ClientOrder);
    }

    @PostMapping("/{orderId}/ship")
    public ResponseEntity<ClientOrder> shipOrder(@PathVariable Long orderId) {
        ClientOrder updatedOrder = orderService.shipOrder(orderId);

        return ResponseEntity.ok(updatedOrder);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<ClientOrder> cancelOrder(@PathVariable Long orderId) {
        ClientOrder updatedOrder = orderService.cancelOrder(orderId);

        return ResponseEntity.ok(updatedOrder);
    }
}

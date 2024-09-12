package com.intercontrat.project.dtos;

import com.intercontrat.project.enums.OrderStatus;

import java.util.Date;
import java.util.List;

public record OrderDTO (
        Long id,
        OrderStatus status,
        Date orderDate,
        Date deliveryDate,
        String address,
        List<ProductDTO> products
) {
}

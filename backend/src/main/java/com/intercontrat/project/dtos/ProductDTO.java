package com.intercontrat.project.dtos;

public record ProductDTO(
        Long id,
        String name,
        String description,
        float price,
        int quantity
) {
}

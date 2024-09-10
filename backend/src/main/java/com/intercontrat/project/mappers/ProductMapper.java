package com.intercontrat.project.mappers;

import com.intercontrat.project.dtos.ProductDTO;
import com.intercontrat.project.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);

    List<ProductDTO> toProductDTOs(List<Product> products);
}

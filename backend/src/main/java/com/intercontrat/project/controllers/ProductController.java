package com.intercontrat.project.controllers;

import com.intercontrat.project.dtos.ProductDTO;
import com.intercontrat.project.entities.Product;
import com.intercontrat.project.mappers.ProductMapper;
import com.intercontrat.project.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product createdProduct = productService.createProduct(productMapper.toProduct(productDTO));

        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        return ResponseEntity.ok(productMapper.toProductDTOs(products));
    }
}

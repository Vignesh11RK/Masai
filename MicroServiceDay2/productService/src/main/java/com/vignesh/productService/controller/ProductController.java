package com.vignesh.productService.controller;


import com.vignesh.productService.model.Product;
import com.vignesh.productService.repository.ProductRepository;
import com.vignesh.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 1. Add product (POST /products)
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // 2. Get product by ID (GET /products/{id})
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // 3. List all products (GET /products)
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
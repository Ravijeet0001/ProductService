package com.scaler.productservicedec25.controllers;


import com.scaler.productservicedec25.models.Product;
import com.scaler.productservicedec25.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }
}
